package github.ittranslationclub.askme.api.fegin.openai;

import github.ittranslationclub.askme.api.vo.openai.ChatMessagesVo;
import github.ittranslationclub.askme.api.vo.openai.ChatResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @program: askme
 * @description: OpenAi 聊天相关接口定义类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 16:41
 **/
@FeignClient("askme-chatgpt")
public interface OpenAiChatService {
    @RequestMapping("/askme/openai/chat/askquestion")
    public Optional<ChatResultVo> askByChatPattern(ChatMessagesVo message);
}
