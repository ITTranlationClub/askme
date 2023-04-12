package github.ittranslationclub.askme.chatgpt.infrastructure.annotation;/*
 * ClassName: IpMax
 * Description:
 * @Author: zjh
 * @Create: 2023/4/12
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ip 在规定时间内允许的最大访问次数
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IpMax {

    /**
     * 允许访问的最大次数
     */
    int count() default Integer.MAX_VALUE;

    /**
     * 时间段，单位为秒，默认值三十秒
     */
    int time() default 30;


}
