package github.ittranslationclub.askme.chatgpt.api.controller;

import cn.hutool.json.JSONUtil;
import com.theokanning.openai.completion.chat.ChatCompletionChunk;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import github.ittranslationclub.askme.chatgpt.app.service.AiTranslationService;
import github.ittranslationclub.common.log.LogAdvice;
import io.reactivex.functions.Consumer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @program: askme
 * @description: 流传输的方式传递 数据
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-10 23:51
 **/
@Slf4j
@RestController
@RequestMapping("/chatFlow")
@Tag(name = "chatGPT 结果流控制器", description = "当微服务不可用时,使用该接口定位问题原因")
public class ChatGPTFileController {

    OpenAiService openAiService;

    @Autowired
    public ChatGPTFileController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @LogAdvice
    @Operation(summary = "提交问题,返回结果流")
    @RequestMapping("/askquestion")
    public void download(HttpServletResponse response, @RequestBody List<ChatMessage> chatMessages) throws IOException {
        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .model("gpt-3.5-turbo")
                .messages(chatMessages)
                .build();

        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type","text/plain");
        // 设置编码
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + "TestResult");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");

        // 内部方法 保证不会文本互串
        OutputStream outputStream = response.getOutputStream();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    openAiService.streamChatCompletion(chatCompletionRequest)
                            .doOnError(Throwable::printStackTrace)
                            .blockingForEach(new Consumer<ChatCompletionChunk>() {
                                @Override
                                public void accept(ChatCompletionChunk chatCompletionChunk) throws Exception {
                                    String resultJson = JSONUtil.toJsonStr(chatCompletionChunk);
                                    outputStream.write(resultJson.getBytes(StandardCharsets.UTF_8));
                                    outputStream.flush();
                                }
                            });
                } catch (Exception e) {
                    throw e;
                } finally {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
