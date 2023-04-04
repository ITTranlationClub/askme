package github.ittranslationclub.askme.chatgpt;

import github.ittranslationclub.remote.chatgpt.auto.EnableAskMeChatGPTProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: askme
 * @description: chatgpt 应用启动类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-29 17:02
 **/
@EnableAskMeChatGPTProvider
@SpringBootApplication
//@EnableDiscoveryClient
public class ChatGPTApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatGPTApplication.class, args);
    }
}
