package top.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * ClassName: ProxyCreator
 * Description:
 * date: 2020/9/9 9:44
 *
 * @author crwen
 * @create 2020-09-09-9:44
 * @since JDK 1.8
 */
public class ProxyCreator {

    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor) {
        return Enhancer.create(targetClass, methodInterceptor);
    }
}
