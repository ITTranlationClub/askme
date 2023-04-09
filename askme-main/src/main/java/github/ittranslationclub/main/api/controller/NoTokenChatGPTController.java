package github.ittranslationclub.main.api.controller;

import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.common.log.LogAdvice;
import github.ittranslationclub.remote.chatgpt.service.ChatGPTRemoteService;
import github.ittranslationclub.utils.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: askme
 * @description: chatGPT功能控制器,用于http调用, 方便定位问题
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-01 19:36
 **/
@Slf4j
@RestController
@RequestMapping("/notoken/chatgpt")
@Tag(name = "notoken-chatGPT 相关功能控制器", description = "不进行 token 验证的 chatgpt 聊天接口")
public class NoTokenChatGPTController {

    ChatGPTRemoteService chatGPTRemoteService;

    @Autowired
    public NoTokenChatGPTController(ChatGPTRemoteService chatGPTRemoteService) {
        this.chatGPTRemoteService = chatGPTRemoteService;
    }

    @LogAdvice
    @Operation(summary = "提交问题,并获取AI结果")
    @PostMapping(value = "/askquestion")
    public Result<String> askQuestion(@RequestBody ChatMessagesDto chatMessagesDto) throws Exception {
        // 增加业务判断逻辑
        Result<ChatResultDto> chatResultDto = chatGPTRemoteService.sendQuestion(chatMessagesDto);

        // TODO 思考如何优雅处理
        String result = chatResultDto.getData().getChoices().get(0).getMessage().getContent();
        return Result.ok(result);
    }

    @LogAdvice
    @Operation(summary = "提交进阶问题,并获取AI结果")
    @PostMapping(value = "/askProQuestion")
    public Result<String> askProQuestion(@RequestBody ChatMessagesDto chatMessagesDto) throws Exception {
        // 增加业务判断逻辑
        Result<ChatResultDto> chatResultDto = chatGPTRemoteService.sendProQuestion(chatMessagesDto);

        // TODO 思考如何优雅处理
        String result = chatResultDto.getData().getChoices().get(0).getMessage().getContent();
        return Result.ok(result);
    }
}
