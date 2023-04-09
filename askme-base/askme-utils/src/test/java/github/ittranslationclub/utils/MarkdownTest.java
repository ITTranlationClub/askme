package github.ittranslationclub.utils;

import cn.hutool.json.JSONUtil;
import github.ittranslationclub.common.dto.openai.ChatMessagesDto;
import github.ittranslationclub.common.dto.openai.ChatResultDto;
import github.ittranslationclub.common.dto.openai.base.MessageItemDto;
import github.ittranslationclub.utils.file.FileUtils;
import github.ittranslationclub.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;

/**
 * @program: askme
 * @description: 测试 chatgpt接口
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-06 16:20
 **/
@Slf4j
public class MarkdownTest {

    static String TRANS_STR = "翻译为中文并保留markdown格式:\n";
    static String CHAT_GPT_KEY = "Bearer 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";
    static Proxy LOCAL_PROXY = new Proxy(Proxy.Type.HTTP,
            new InetSocketAddress("127.0.0.1", 60880));
    static String BASE_PATH = "C:\\`book\\en\\";
    static String RESULT_PATH = "C:\\`book\\zh\\";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String filePath = scanner.nextLine();
            String info = FileUtils.readFile(BASE_PATH + filePath);
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(TRANS_STR).append(info);
            ChatMessagesDto messagesDto = new ChatMessagesDto();
            List<MessageItemDto> messageItemDtos = new ArrayList<>();

            MessageItemDto messageItemDto = new MessageItemDto();
            messageItemDto.setContent(stringBuilder.toString());
            messageItemDtos.add(messageItemDto);
            messagesDto.setMessages(messageItemDtos);

            log.info("构造的参数内容为: {}", JSONUtil.toJsonStr(messagesDto));
            System.out.println();
            try {
                ChatResultDto chatResult = askQuestionWithKey(messagesDto, CHAT_GPT_KEY).get();
                String content = chatResult.getChoices().get(0).getMessage().getContent();
                log.info(content);
                FileUtils.writeFile(RESULT_PATH + filePath, content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Optional<ChatResultDto> askQuestionWithKey(ChatMessagesDto message, String key) throws Exception {
        // openAi 相关配置参数在 service 层处理
        message.setModel("gpt-3.5-turbo");
        for(MessageItemDto messageItemVo: message.getMessages()) {
            messageItemVo.setRole("user");
        }
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("content",
                "application/json");
        headerMap.put("Authorization",
                "Bearer " + key);

        // 尝试获取结果, 无结果抛出异常
        String result = HttpUtils.httpsPost(
                "https://api.openai.com/v1/chat/completions", JSONUtil.toJsonStr(message),
                headerMap, LOCAL_PROXY
        );
        ChatResultDto chatResultVo = JSONUtil.toBean(result, ChatResultDto.class);
        if(chatResultVo==null || chatResultVo.getChoices()==null)
            throw new Exception(result);
        return Optional.ofNullable(chatResultVo);
    }
}
