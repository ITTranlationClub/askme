package github.ittranslationclub.utils.http;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;

/**
 * @program: askme
 * @description: Http 工具包装类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-03-25 16:06
 **/
@Slf4j
public class HttpUtils {

    static {
        HttpRequest.setGlobalTimeout(500000);
    }
    /**
     * 发送HTTP GET请求
     *
     * @param url 请求地址
     * @return 请求结果
     */
    public static String get(String url) {
        return HttpRequest.get(url).execute().body();
    }

    /**
     * 发送HTTP GET请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求结果
     */
    public static String get(String url, Map<String, Object> params) {
        return HttpRequest.get(url).form(params).execute().body();
    }

    /**
     * 发送HTTP POST请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 请求结果
     */
    public static String post(String url, Map<String, Object> params) {
        return HttpRequest.post(url).form(params).execute().body();
    }

    /**
     * 发送HTTPS GET请求
     *
     * @param url 请求地址
     * @return 请求结果
     */
    public static String httpsGet(String url) {
        return HttpRequest.get(url)
                .setSSLSocketFactory(SSLUtils.getSSLSocketFactory())
                .execute().body();
    }

    /**
     * 发送HTTPS POST请求
     *
     * @param url    请求地址
     * @param paramBody post请求 boy信息
     * @return 请求结果
     */
    public static String httpsPost(String url, String paramBody, Map<String,String> header, Proxy proxy) {
        log.info("httpsPost params is Url: {}, paramBody: {}, header: {}", url, paramBody, JSONUtil.toJsonStr(header));
        HttpRequest httpRequest = HttpRequest.post(url)
                .body(paramBody)
                .setSSLSocketFactory(SSLUtils.getSSLSocketFactory());
        if(proxy != null)
            httpRequest.setProxy(proxy);

        for(Map.Entry<String, String> entry: header.entrySet()) {
            httpRequest.header(entry.getKey(), entry.getValue());
        }
        String result = httpRequest.execute().body();
        log.info("httpsPost methods result is {}", result);
        return result;
    }
}
