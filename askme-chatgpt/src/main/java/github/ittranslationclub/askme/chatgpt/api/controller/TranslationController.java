package github.ittranslationclub.askme.chatgpt.api.controller;

import cn.hutool.core.io.FileUtil;
import github.ittranslationclub.askme.chatgpt.app.service.AiTranslationService;
import github.ittranslationclub.askme.chatgpt.app.service.MarkdownTranslationService;
import github.ittranslationclub.askme.chatgpt.app.service.OpenAiChatService;
import github.ittranslationclub.askme.chatgpt.infrastructure.config.ArticleConfig;
import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.common.dto.openai.base.ChoicesItemDto;
import github.ittranslationclub.common.dto.openai.base.MessageItemDto;
import github.ittranslationclub.common.log.LogAdvice;
import github.ittranslationclub.utils.file.BatchFileUtils;
import github.ittranslationclub.utils.file.FileUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @program: askme
 * @description: 翻译控制器实现代码
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-11 12:00
 **/
@Slf4j
@RestController
@RequestMapping("/translation")
@Tag(name = "翻译控制器", description = "不对外开放, 通过 getBean 调用")
public class TranslationController {

    ArticleConfig articleConfig;

    OpenAiChatService openAiChatService;

    AiTranslationService aliyunTranslationServiceImpl;

    @Autowired
    public TranslationController(ArticleConfig articleConfig,
                                 OpenAiChatService openAiChatService,
                                 AiTranslationService aliyunTranslationServiceImpl) {
        this.articleConfig = articleConfig;
        this.openAiChatService = openAiChatService;
        this.aliyunTranslationServiceImpl = aliyunTranslationServiceImpl;
    }

    @LogAdvice
    @Operation(summary = "批量翻译文本文件, 并写入新文件")
    @GetMapping("/fileScan")
    
    public void translationFileByBatch(@RequestParam String filePath) {
        BatchFileUtils.scanFiles(filePath,
                openAiChatService::transMarkDownToFile);
    }

}
