package github.ittranslationclub.askme.chatgpt.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @program: askme
 * @description: ChatGPT 相关配置信息
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 16:24
 **/
@RefreshScope
@Data
@Configuration
public class ChatGPTConfig {

    @Value("${open.base.chatProxyUrl}")
    private String chatProxyUrl;

    @Value("${open.base.key}")
    private String key;

    @Value("${open.base.proKey}")
    private String proKey;

    @Value("${open.api.chatUrl}")
    private String chatUrl;

    @Value("${open.api.editUrl}")
    private String editUrl;
    @Value("${open.api.creatImgUrl}")
    private String creatImgUrl;
    @Value("${open.api.editImgUrl}")
    private String editImgUrl;

    @Value("${open.api.variationImgUrl}")
    private String variationImgUrl; // 变换图片

    @Value("${open.model.gpt-model}")
    private String gptModel;

    @Value("${open.proxy.url}")
    private String proxyUrl;

    @Value("${open.proxy.port}")
    private String proxyPort;

    private Proxy proxy;

    public Proxy getChatProxy() {
        if(proxy == null ) {
            if(StringUtils.isEmpty(proxyPort) || StringUtils.isEmpty(proxyUrl)) {
                return null;
            }
            proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress(proxyUrl, Integer.parseInt(proxyPort)));
        }
        // 添加逻辑, 避免每次都进行更新
        // proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUrl, Integer.parseInt(proxyPort)));
        return proxy;
    }

}
