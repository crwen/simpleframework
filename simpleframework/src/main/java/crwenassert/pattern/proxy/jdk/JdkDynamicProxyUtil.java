package crwenassert.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * ClassName: JdkDynamicProxyUtil
 * Description:
 * date: 2020/9/4 17:23
 *
 * @author crwen
 * @create 2020-09-04-17:23
 * @since JDK 1.8
 */
public class JdkDynamicProxyUtil {
    public static <T> T newProxyInstance(T targetObject,
                                         InvocationHandler handler) {
        ClassLoader classLoader = targetObject.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }
}
