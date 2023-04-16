package github.ittranslationclub.askme.chatgpt.infrastructure.config;/*
 * ClassName: AdminWebConfig
 * Description:
 * @Author: zjh
 * @Create: 2023/4/12
 */

import github.ittranslationclub.askme.chatgpt.infrastructure.interceptor.JwtInterceptor;
import github.ittranslationclub.askme.chatgpt.infrastructure.utils.IpCount;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminWebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(new IpCount(new RedisTemplate())))
                .addPathPatterns("/**");  //所有请求都被拦截包括静态资源
    }

}
