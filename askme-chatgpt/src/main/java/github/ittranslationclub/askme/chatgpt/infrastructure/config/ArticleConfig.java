package github.ittranslationclub.askme.chatgpt.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @program: askme
 * @description: 翻译文章相关参数配置类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-11 15:32
 **/
@RefreshScope
@Data
@Configuration
public class ArticleConfig {

    @Value("${open.article.preContent}")
    private String preContent;

}
