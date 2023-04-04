package github.ittranslationclub.utils;

import github.ittranslationclub.utils.json.ObjectMappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 增强bean配置类
 */
@AutoConfiguration
public class EnhanceAutoBeanConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return ObjectMappers.configMapper();
    }

}
