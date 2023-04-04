package github.ittranslationclub.askme.chatgpt.app.service;

import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;

import java.util.Optional;

/**
 * @program: askme
 * @description: OpenAi 聊天相关接口定义类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 16:41
 **/
public interface OpenAiChatService {

    Optional<ChatResultDto> askByChatPattern(ChatMessagesDto message) throws Exception;

    Optional<ChatResultDto> askByProChatPattern(ChatMessagesDto message) throws Exception;
}
