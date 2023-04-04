package github.ittranslationclub.remote.chatgpt.auto;

import github.ittranslationclub.remote.chatgpt.config.AskMeChatGPTProviderConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * auth-life 服务提供接口的引用
 *
 * @author gaoxiang
 * @date 2022/12/23 14:50
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AskMeChatGPTProviderConfiguration.class)
public @interface EnableAskMeChatGPTProvider {

}
