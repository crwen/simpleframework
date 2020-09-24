package top.simpleframework.mvc.annotation;

import top.simpleframework.mvc.type.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: RequestMapping
 * Description:
 * date: 2020/9/12 9:58
 *
 * @author crwen
 * @create 2020-09-12-9:58
 * @since JDK 1.8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    // 请求路径
    String value() default "";
    // 请求方法
    RequestMethod method() default RequestMethod.GET;
}
