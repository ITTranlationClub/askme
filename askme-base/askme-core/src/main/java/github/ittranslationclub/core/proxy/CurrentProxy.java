package github.ittranslationclub.core.proxy;

import org.springframework.aop.framework.AopContext;

/**
 * 获取当前接口的代理对象
 *
 * @author hehedada
 * @date 2022/12/30 13:41
 */
public interface CurrentProxy<T> {

    @SuppressWarnings("unchecked")
    default T self(){
        return (T) AopContext.currentProxy();
    }

}
