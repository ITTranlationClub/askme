package github.ittranslationclub.askme.chatgpt.infrastructure.interceptor;/*
 * ClassName: JwtInterceptor
 * Description:
 * @Author: zjh
 * @Create: 2023/4/12
 */

import github.ittranslationclub.askme.chatgpt.infrastructure.annotation.IpMax;
import github.ittranslationclub.askme.chatgpt.infrastructure.utils.IpCount;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    /**
     * 用来验证ip访问次数的逻辑层
     */
    private IpCount ipCount;

    public JwtInterceptor(IpCount ipCount) {
        this.ipCount = ipCount;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {

        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();


        // ip超频访问方面的验证   start
        if (method.isAnnotationPresent(IpMax.class)) { // 判断访问的接口是否有此注解
            String requestURI = request.getRequestURI(); // 请求的url
            String ip = Lang.getIP(request); // 获得请求者的ip（可根据自己的方法，我这边用的是nutz的工具类，引入请参考历史文章）
            IpMax ipMax = method.getAnnotation(IpMax.class); // 获得注解中的内容
            int count = ipMax.count(); // 访问次数
            long time = ipMax.time(); //  时间范围
            // 通过封装的方法，判断ip是否可以通过验证
            boolean flag = ipCount.ipIsOk(requestURI, ip, count, time);
            if (!flag) { // 超过访问次数
                // 时间范围内超出最大访问次数   注：超过访问次数的处理方式可自行根据具体需求
                throw new RuntimeException("本接口" + time + "秒内可以请求" + count + "次，您已超出最大访问次数！！！");
            }
        }
        // ip超频访问方面的验证   end

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}