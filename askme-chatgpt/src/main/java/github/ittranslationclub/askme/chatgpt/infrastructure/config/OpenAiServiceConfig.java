package github.ittranslationclub.askme.chatgpt.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @program: askme
 * @description: 创建 OpenAiService Bean对象
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-10 21:38
 **/
@Configuration
public class OpenAiServiceConfig {

    ChatGPTConfig chatGPTConfig;

    @Autowired
    public OpenAiServiceConfig(ChatGPTConfig chatGPTConfig) {
        this.chatGPTConfig = chatGPTConfig;
    }

    @Bean
    public OpenAiService openAiService() {
        if(chatGPTConfig.getChatProxy() == null)
            return new OpenAiService(chatGPTConfig.getKey());

        ObjectMapper mapper = OpenAiService.defaultObjectMapper();
        Proxy proxy = chatGPTConfig.getChatProxy();
        OkHttpClient client = OpenAiService.defaultClient(chatGPTConfig.getKey(), ChatGPTConfig.DEFAULT_TIMEOUT)
                .newBuilder()
                .proxy(proxy)
                .build();
        Retrofit retrofit = OpenAiService.defaultRetrofit(client, mapper);
        OpenAiApi api = retrofit.create(OpenAiApi.class);
        return new OpenAiService(api);
    }
}
