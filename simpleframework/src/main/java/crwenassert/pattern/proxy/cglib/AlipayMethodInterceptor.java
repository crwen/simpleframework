package crwenassert.pattern.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * ClassName: AlipayMethodInterceptor
 * Description:
 * date: 2020/9/4 17:59
 *
 * @author crwen
 * @create 2020-09-04-17:59
 * @since JDK 1.8
 */
public class AlipayMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        beforePay();
        Object result = proxy.invokeSuper(obj, args);
        afterPay();
        return result;
    }

    private void afterPay() {
        System.out.println("支付给华为云");
    }

    private void beforePay() {
        System.out.println("从农行取款");
    }
}
