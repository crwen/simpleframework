package top.simpleframework.aop.aspect;

import java.lang.reflect.Method;

/**
 * ClassName: DefaultAspect
 * Description:
 * date: 2020/9/9 0:05
 *
 * @author crwen
 * @create 2020-09-09-0:05
 * @since JDK 1.8
 */
public abstract class DefaultAspect {
    /**
     *  事前拦截
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     */
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {

    }

    /**
     * 事后拦截
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @param returnValue 别代理的目标方法执行后的返回值
     * @return
     */
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object returnValue) throws Throwable  {
        return returnValue;
    }

    /**
     *
     * @param targetClass 被代理的目标类
     * @param method 被代理的目标方法
     * @param args 被代理的目标方法对应的参数列表
     * @param e 被代理的目标方法抛出的异常
     * @throws Throwable
     */
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable e) throws Throwable {
        throw e;
    }
}
