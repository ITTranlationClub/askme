package github.ittranslationclub.utils.exception;

import github.ittranslationclub.utils.exception.handle.ExceptionHandle;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 配置异常处理类通知
 *
 * @author hehedada
 * @date 2023/3/13 14:31
 */
@AutoConfiguration
public class ExceptionConfig {

    @Bean
    public ExceptionHandle exceptionHandle() {
        return new ExceptionHandle();
    }

}
