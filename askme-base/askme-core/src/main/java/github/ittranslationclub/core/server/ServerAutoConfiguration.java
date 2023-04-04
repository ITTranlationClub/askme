package github.ittranslationclub.core.server;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;
import github.ittranslationclub.redis.start.service.StringRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 服务注册到nacos类
 *
 * @author hehedada
 */
@Slf4j
@AutoConfiguration
public class ServerAutoConfiguration {

    @Bean
    @Primary
    public NacosServiceRegistry customServiceRegistry(NacosDiscoveryProperties nacosDiscoveryProperties,
                                                      StringRedisService stringRedisService) {
        return new CustomServiceRegistry(nacosDiscoveryProperties, stringRedisService);
    }

}
