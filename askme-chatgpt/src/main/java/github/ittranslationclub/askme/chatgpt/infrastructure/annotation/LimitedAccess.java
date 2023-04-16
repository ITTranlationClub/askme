package github.ittranslationclub.askme.chatgpt.infrastructure.annotation;/*
 * ClassName: LimitedAccess
 * Description:
 * @Author: zjh
 * @Create: 2023/4/11
 */

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitedAccess {
    /**
     * 从第一次访问接口的时间到周期时间内，最大访问频率次，默认60次
     * @return
     */
    long frequency() default 60;

    /**
     * 周期时间,默认30分钟内
     * @return
     */
    long second() default 30*60;
}
