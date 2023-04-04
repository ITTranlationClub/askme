package github.ittranslationclub.remote.chatgpt.service;

import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.utils.result.Result;


/**
 * auth-life 服务 暴露的远程接口
 *
 * @author gaoxiang
 * @date 2022/12/23 14:17
 */
public interface ChatGPTRemoteService {

    Result<ChatResultDto> sendQuestion(ChatMessagesDto messagesDto) throws Exception;

    Result<ChatResultDto> sendProQuestion(ChatMessagesDto messagesDto) throws Exception;
}
