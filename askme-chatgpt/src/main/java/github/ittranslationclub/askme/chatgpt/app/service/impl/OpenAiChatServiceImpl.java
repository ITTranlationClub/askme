package github.ittranslationclub.askme.chatgpt.app.service.impl;/*
 * ClassName: ChatGPTAPI
 * Description:
 * @Author: zjh
 * @Create: 2023/3/25
 */

import cn.hutool.json.JSONUtil;
import github.ittranslationclub.askme.chatgpt.app.service.OpenAiChatService;
import github.ittranslationclub.askme.chatgpt.infrastructure.config.ChatGPTConfig;
import github.ittranslationclub.askme.chatgpt.infrastructure.constant.ChatGPTConstant;
import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.common.dto.openai.base.MessageItemDto;
import github.ittranslationclub.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class OpenAiChatServiceImpl implements OpenAiChatService {

    ChatGPTConfig chatGPTConfig;

    @Autowired
    public OpenAiChatServiceImpl(ChatGPTConfig chatGPTConfig) {
        this.chatGPTConfig = chatGPTConfig;
    }

    @Override
    public Optional<ChatResultDto> askByChatPattern(ChatMessagesDto message) throws Exception {
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
}
