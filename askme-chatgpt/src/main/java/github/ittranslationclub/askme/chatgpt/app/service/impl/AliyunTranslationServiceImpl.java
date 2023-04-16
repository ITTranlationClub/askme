package github.ittranslationclub.askme.chatgpt.app.service.impl;


import cn.hutool.core.io.FileUtil;
import com.aliyun.alimt20181012.Client;
import com.aliyun.alimt20181012.models.TranslateRequest;
import com.aliyun.alimt20181012.models.TranslateResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.Common;
import com.aliyun.teautil.models.RuntimeOptions;
import github.ittranslationclub.askme.chatgpt.app.service.AiTranslationService;
import github.ittranslationclub.askme.chatgpt.app.service.MarkdownTranslationService;
import github.ittranslationclub.common.log.LogAdvice;
import github.ittranslationclub.utils.file.BatchFileUtils;
import github.ittranslationclub.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @program: askme
 * @description: AI翻译接口实现类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-11 21:33
 **/
@Slf4j
@Service
public class AliyunTranslationServiceImpl implements AiTranslationService, MarkdownTranslationService {

    Client aliyunClient;

    @Autowired
    public AliyunTranslationServiceImpl(Client aliyunClient) {
        this.aliyunClient = aliyunClient;
    }

    @LogAdvice
    @Override
    public String transMarkDown(String sourceTextList) throws Exception {
        TranslateRequest translateRequest = new TranslateRequest();
        // 思考如何优化, 避免使用序号
        translateRequest.setSourceLanguage("en").setTargetLanguage("zh")
                .setScene("description")
                .setSourceText(sourceTextList)
                .setFormatType("html");
        RuntimeOptions runtime = new RuntimeOptions();

        TranslateResponse response = aliyunClient.translateWithOptions(translateRequest, runtime);
        String transStr = response.getBody().getData().translated;
        log.info("transMarkDown result is {}", transStr);
        return transStr;
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

        for(String strBuff: contentList) {

            String reText = null;
            try {
                reText = transMarkDown(strBuff);
            } catch (Exception e) {
                log.error("transMarkDown Method error.", e);
            }
            FileUtils.writeFile(fileNameBuilder.toString(), reText);
        }

    }

}
