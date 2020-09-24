package top.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * ClassName: Aspect
 * Description:
 * date: 2020/9/8 23:58
 *
 * @author crwen
 * @create 2020-09-08-23:58
 * @since JDK 1.8
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 需要被织入横切逻辑的注解标签
     * @return
     */
    //Class<? extends Annotation> value();
    String pointcut();
}
