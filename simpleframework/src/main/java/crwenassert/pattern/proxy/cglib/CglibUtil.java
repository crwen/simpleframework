package crwenassert.pattern.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * ClassName: CglibUtil
 * Description:
 * date: 2020/9/4 18:01
 *
 * @author crwen
 * @create 2020-09-04-18:01
 * @since JDK 1.8
 */
public class CglibUtil {

    public static <T> T createProxy(T targetObject, MethodInterceptor methodInterceptor) {
        return (T) Enhancer.create(targetObject.getClass(), methodInterceptor);
    }
}
