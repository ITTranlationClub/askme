package github.ittranslationclub.main;

import github.ittranslationclub.remote.chatgpt.auto.EnableAskMeChatGPTConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: askme
 * @description: 主程序启动类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-02 17:01
 **/
@EnableAskMeChatGPTConsumer
@SpringBootApplication
//@EnableDiscoveryClient
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
