package crwenassert.aspect;

import lombok.extern.slf4j.Slf4j;
import top.simpleframework.aop.annotation.Aspect;
import top.simpleframework.aop.annotation.Order;
import top.simpleframework.aop.aspect.DefaultAspect;

import java.lang.reflect.Method;

/**
 * ClassName: ControllerTimeCaculatorAspect
 * Description:
 * date: 2020/9/9 10:51
 *
 * @author crwen
 * @create 2020-09-09-10:51
 * @since JDK 1.8
 */
@Aspect(pointcut = "within(top.simpleframework.core.annotation.Component)")
@Order(0)
@Slf4j
public class ServiceTimeCaculatorAspect extends DefaultAspect {

    private long timestampCache;

    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        log.info("开始计时，执行的类是 [{}]，执行的方法是[{}]，参数是[{}]",
                targetClass.getName(), method.getName(), args);
        timestampCache = System.currentTimeMillis();
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable {
        long endTime = System.currentTimeMillis();
        long costTime = endTime - timestampCache;
        log.info("计时结束，执行的类是 [{}]，执行的方法是[{}]，参数是[{}], 耗时[{}]ms",
                targetClass.getName(), method.getName(), args, costTime);
        return returnValue;
    }
}
