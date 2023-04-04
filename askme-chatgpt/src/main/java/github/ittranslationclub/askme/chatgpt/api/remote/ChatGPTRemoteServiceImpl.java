package github.ittranslationclub.askme.chatgpt.api.remote;

import github.ittranslationclub.askme.chatgpt.app.service.OpenAiChatService;
import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.common.log.LogAdvice;
import github.ittranslationclub.remote.chatgpt.service.ChatGPTRemoteService;
import github.ittranslationclub.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @program: askme
 * @description: chatGPT 远程调用接口实现类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-01 19:09
 **/
@Slf4j
@Service
public class ChatGPTRemoteServiceImpl implements ChatGPTRemoteService {

    OpenAiChatService openAiChatService;

    @Autowired
    public ChatGPTRemoteServiceImpl(OpenAiChatService openAiChatService) {
        this.openAiChatService = openAiChatService;
    }

    @LogAdvice(name = "ChatGPT 提问接口,提交问题")
    @Override
    public Result<ChatResultDto> sendQuestion(ChatMessagesDto messagesDto) throws Exception {
        ChatResultDto chatResultDto = openAiChatService.askByChatPattern(messagesDto).get();
        log.info("sendQuestion result is {}.", chatResultDto);
        return Result.ok(chatResultDto);
    }

    @LogAdvice
    @Override
    public Result<ChatResultDto> sendProQuestion(ChatMessagesDto messagesDto) throws Exception {
        ChatResultDto chatResultDto = openAiChatService.askByProChatPattern(messagesDto).get();
        log.info("sendProQuestion result is {}.", chatResultDto);
        return Result.ok(chatResultDto);
    }

}
