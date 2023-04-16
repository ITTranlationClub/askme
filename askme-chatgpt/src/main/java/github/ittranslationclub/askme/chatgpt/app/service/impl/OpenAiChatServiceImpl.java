package github.ittranslationclub.askme.chatgpt.app.service.impl;/*
 * ClassName: ChatGPTAPI
 * Description:
 * @Author: zjh
 * @Create: 2023/3/25
 */

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import github.ittranslationclub.askme.chatgpt.app.service.MarkdownTranslationService;
import github.ittranslationclub.askme.chatgpt.app.service.OpenAiChatService;
import github.ittranslationclub.askme.chatgpt.infrastructure.config.ArticleConfig;
import github.ittranslationclub.askme.chatgpt.infrastructure.config.ChatGPTConfig;
import github.ittranslationclub.askme.chatgpt.infrastructure.constant.ChatGPTConstant;
import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.common.dto.openai.base.ChoicesItemDto;
import github.ittranslationclub.common.dto.openai.base.MessageItemDto;
import github.ittranslationclub.common.log.LogAdvice;
import github.ittranslationclub.utils.file.BatchFileUtils;
import github.ittranslationclub.utils.file.FileUtils;
import github.ittranslationclub.utils.http.HttpUtils;
import io.reactivex.functions.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
public class OpenAiChatServiceImpl implements OpenAiChatService, MarkdownTranslationService {

    ChatGPTConfig chatGPTConfig;
    ArticleConfig articleConfig;
    OpenAiService openAiService;

    @Autowired
    public OpenAiChatServiceImpl(ChatGPTConfig chatGPTConfig,
                                 ArticleConfig articleConfig,
                                 OpenAiService openAiService) {
        this.chatGPTConfig = chatGPTConfig;
        this.articleConfig = articleConfig;
        this.openAiService = openAiService;
    }

    @LogAdvice
    @Override
//    @Retryable(value = Exception.class,maxAttempts = 4,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public Optional<ChatResultDto> askByChatPattern(ChatMessagesDto message) throws Exception {
        if(JSONUtil.toJsonStr(message).length()>chatGPTConfig.getLimitLength())
            throw new Exception("输入的文本过程, 请使用专业访问接口");
        return askQuestionWithKey(message, chatGPTConfig.getKey());
    }

    @Override
    public Optional<ChatResultDto> askByProChatPattern(ChatMessagesDto message) throws Exception {
        return askQuestionWithKey(message, chatGPTConfig.getProKey());
    }

    private Optional<ChatResultDto> askQuestionWithKey(ChatMessagesDto message, String key) throws Exception {
        // openAi 相关配置参数在 service 层处理
        message.setModel(chatGPTConfig.getGptModel());
        for(MessageItemDto messageItemVo: message.getMessages()) {
            messageItemVo.setRole(ChatGPTConstant.USER);
        }
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(ChatGPTConstant.CONTENT_TYPE,
                ChatGPTConstant.APPLICATION_JSON);
        headerMap.put(ChatGPTConstant.AUTHORIZATION,
                ChatGPTConstant.BEARER + key);

        // 尝试获取结果, 无结果抛出异常
        String result = HttpUtils.httpsPost(
                chatGPTConfig.getChatUrl(), JSONUtil.toJsonStr(message),
                headerMap, chatGPTConfig.getChatProxy()
        );
        ChatResultDto chatResultVo = JSONUtil.toBean(result, ChatResultDto.class);
        if(chatResultVo==null || chatResultVo.getChoices()==null)
            throw new Exception(result);
        return Optional.ofNullable(chatResultVo);
    }

    @Override
    public void transMarkDownToFile(File file) {
        // 不考虑空间, 这点文本量 没必要麻烦自己
        String fileStr = FileUtils.readFile(file);
        List<String> contentList = BatchFileUtils.spiltIntoSize(fileStr);

        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(file.getParent())
                .append("\\")
                .append("zhType")
                .append(file.getName());
        int writeNum = 0;

        for(String strBuff: contentList) {

            ChatMessagesDto messagesDto = new ChatMessagesDto();
            List<MessageItemDto> messageItemDtoList = new ArrayList<>();
            MessageItemDto messageItemDto = new MessageItemDto();
            messageItemDto.setContent(articleConfig.getPreContent()+strBuff);
            messageItemDtoList.add(messageItemDto);
            messagesDto.setMessages(messageItemDtoList);

            // 发送请求, 翻译文本内容
            StringBuilder transBuilder = new StringBuilder();
            ChatResultDto resultDto = null;
            try {
                resultDto = askByChatPattern(messagesDto).get();
            } catch (Exception e) {
                log.error("askByChatPattern methods error", e);
            }
            List<ChoicesItemDto> choicesList = resultDto.getChoices();
            for(ChoicesItemDto choicesItem: choicesList) {
                String reContent = choicesItem.getMessage().getContent();
                transBuilder.append(reContent);
            }
            FileUtils.writeFile(fileNameBuilder.toString(), transBuilder.toString());

            // 统计 翻译结果&范围
            writeNum += strBuff.length();
            StringBuilder statisticResult = new StringBuilder();
            statisticResult.append(file.getName()).append(" ")
                    .append(fileStr.length()).append(" ")
                    .append(writeNum).append(System.lineSeparator());
            FileUtils.writeFile("C:\\GitBookRis\\翻译统计.txt", statisticResult.toString());
        }
    }

    private String transBySDK(String message) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRole("user");
        chatMessage.setContent(message);

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(chatMessages)
                .build();

        List<ChatCompletionChoice> chatChoiceList = openAiService.createChatCompletion(chatCompletionRequest)
                .getChoices();
        StringBuilder resultBuilder = new StringBuilder();
        for(ChatCompletionChoice chatCompletionChoice:chatChoiceList) {
            if(chatCompletionChoice == null)
                continue;
            resultBuilder.append(chatCompletionChoice.getMessage().getContent());
        }
        return resultBuilder.toString();

    }
}
