package top.simpleframework.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: Order
 * Description:
 * date: 2020/9/9 0:02
 *
 * @author crwen
 * @create 2020-09-09-0:02
 * @since JDK 1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    /**
     *  控制类的执行顺序，值越小优先级越高
     * @return
     */
    int value();
}
