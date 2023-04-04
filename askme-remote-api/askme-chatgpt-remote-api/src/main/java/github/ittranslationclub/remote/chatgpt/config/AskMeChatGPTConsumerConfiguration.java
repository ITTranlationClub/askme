package github.ittranslationclub.remote.chatgpt.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 服务消费引用的配置类
 *
 * @author gaoxiang
 * @date 2022/12/23 14:28
 */
@AutoConfiguration
@ImportResource("classpath:/remote/askme-dubbo-consumer.xml")
@PropertySource("classpath:/remote/askme.properties")
public class AskMeChatGPTConsumerConfiguration {


}
