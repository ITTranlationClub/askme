package github.ittranslationclub.remote.chatgpt.auto;

import github.ittranslationclub.remote.chatgpt.config.AskMeChatGPTConsumerConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * auth-life 服务消费接口的引用
 *
 * @author gaoxiang
 * @date 2022/12/23 14:50
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AskMeChatGPTConsumerConfiguration.class)
public @interface EnableAskMeChatGPTConsumer {

}
