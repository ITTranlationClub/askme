package github.ittranslationclub.swagger.start.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文档配置类
 *
 * @author hehedada
 * @since 2022/12/20 16:49
 */
@AutoConfiguration
public class Knife4jStartConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    private static final String PATHS_TO_MATCH = "/**";

    private static final String[] PACKAGED_TO_MATCH = {"github.ittranslationclub.main.api.controller"};


    /**
     * 需要扫描的配置
     *
     * @return GroupedOpenApi
     */
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group(applicationName)
                .pathsToMatch(PATHS_TO_MATCH)
                .packagesToScan(PACKAGED_TO_MATCH)
                .build();
    }

    /**
     * 工程 访问 /doc.html, 主页显示的内容说明
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(new Info()
                .title(applicationName + "-api")
                .description(applicationName + "-服务")
                .contact(new Contact().name("ITTranslationGroup"))
                .version("1.0")
                .termsOfService("https://doc.authorization.life/" + applicationName)
                .license(new License().name("MIT").url("https://doc.authorization.life")));
    }
}
