package github.ittranslationclub.remote.chatgpt.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 服务提供暴露接口的配置类
 *
 * @author gaoxiang
 * @date 2022/12/23 14:27
 */
@AutoConfiguration
@ImportResource("classpath:/remote/askme-dubbo-provider.xml")
@PropertySource("classpath:/remote/askme.properties")
public class AskMeChatGPTProviderConfiguration {


}
