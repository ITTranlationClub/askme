package github.ittranslationclub.askme.chatgpt.api.controller;

import github.ittranslationclub.askme.chatgpt.app.service.OpenAiChatService;
import github.ittranslationclub.askme.chatgpt.infrastructure.annotation.IpMax;
import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.common.log.LogAdvice;
import github.ittranslationclub.utils.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: askme
 * @description: chatGPT功能控制器, 用于http调用, 方便定位问题
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-01 19:36
 **/
@Slf4j
@RestController
@RequestMapping("/chatgpt")
@Tag(name = "chatGPT 相关功能控制器", description = "当微服务不可用时,使用该接口定位问题原因")
public class ChatGPTController {

    OpenAiChatService openAiChatService;

    @Autowired
    public ChatGPTController(OpenAiChatService openAiChatService) {
        this.openAiChatService = openAiChatService;
    }

    @LogAdvice
    @Operation(summary = "提交问题,并获取AI结果")
    @PostMapping(value = "/askquestion")
    public Result<String> askQuestion(@RequestBody ChatMessagesDto chatMessagesDto) throws Exception {

        // 增加业务判断逻辑
        ChatResultDto chatResultDto = openAiChatService.askByChatPattern(chatMessagesDto).get();

        // TODO 思考如何优雅处理
        String result = chatResultDto.getChoices().get(0).getMessage().getContent();
        return Result.ok(result);
    }

    @LogAdvice
    @Operation(summary = "提交进阶问题,并获取AI结果")
    @PostMapping(value = "/askproQuestion")
    public Result<String> askProQuestion(@RequestBody ChatMessagesDto chatMessagesDto) throws Exception {
        // 增加业务判断逻辑
        ChatResultDto chatResultDto = openAiChatService.askByProChatPattern(chatMessagesDto).get();

        // TODO 思考如何优雅处理
        String result = chatResultDto.getChoices().get(0).getMessage().getContent();
        return Result.ok(result);
    }

    @IpMax(count = 3, time = 10)
    @GetMapping(value = "/test")
    public Result<String> test() {
        return Result.ok("成功");
    }


}
