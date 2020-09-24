package top.simpleframework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: RequestParam
 * Description: 请求的方法参数名称
 * date: 2020/9/12 10:02
 *
 * @author crwen
 * @create 2020-09-12-10:02
 * @since JDK 1.8
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    // 方法参数名称
    String value() default "";
    // 该参数是否必须
    boolean required() default true;
}
