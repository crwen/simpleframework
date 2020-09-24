package crwenassert.aspect;

import lombok.extern.slf4j.Slf4j;
import top.simpleframework.aop.annotation.Aspect;
import top.simpleframework.aop.annotation.Order;
import top.simpleframework.aop.aspect.DefaultAspect;

import java.lang.reflect.Method;

/**
 * ClassName: ControllerInfoRecordAspect
 * Description:
 * date: 2020/9/9 12:21
 *
 * @author crwen
 * @create 2020-09-09-12:21
 * @since JDK 1.8
 */
@Aspect(pointcut = "within(top.simpleframework.core.annotation.Component)")

@Order(10)
@Slf4j
public class ServiceInfoRecordAspect extends DefaultAspect {
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("方法开始执行了，执行的类是 [{}]，执行的方法是[{}]，参数是[{}]",
                targetClass.getName(), method.getName(), args);

    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        log.info("方法顺利完成，执行的类是 [{}]，执行的方法是[{}]，参数是[{}]，返回值是[{}]",
                targetClass.getName(), method.getName(), args, returnValue);
        return returnValue;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {
        log.info("方法抛出异常，执行的类是 [{}]，执行的方法是[{}]，参数是[{}]，异常是 [{}]",
                targetClass.getName(), method.getName(), args, e.getMessage());
    }
}
