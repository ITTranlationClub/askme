package github.ittranslationclub.askme.chatgpt.infrastructure.config;

import com.aliyun.alimt20181012.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: askme
 * @description: 阿里云翻译配置类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-12 12:02
 **/
@RefreshScope
@Data
@Configuration
public class AliTranslationConfig {

    @Value("${aliyun.accessKey}")
    private String accessKey;

    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;

    @Bean
    public Client aliyunClient() throws Exception {
        Config config = new Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKey)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "mt.aliyuncs.com";
        return new Client(config);
    }
}
