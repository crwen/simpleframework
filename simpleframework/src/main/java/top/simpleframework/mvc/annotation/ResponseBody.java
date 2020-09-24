package top.simpleframework.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: ResponseBody
 * Description: 用于标记自动对返回值进行 json 处理
 * date: 2020/9/12 10:05
 *
 * @author crwen
 * @create 2020-09-12-10:05
 * @since JDK 1.8
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseBody {

}
