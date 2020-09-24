package crwenassert.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ClassName: AlipayInvocationHandler
 * Description:
 * date: 2020/9/4 17:17
 *
 * @author crwen
 * @create 2020-09-04-17:17
 * @since JDK 1.8
 */
public class AlipayInvocationHandler implements InvocationHandler {

    private Object targetObject;

    public AlipayInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforePay();
        Object ret = method.invoke(targetObject, args);
        afterPay();
        return ret;
    }

    private void afterPay() {
        System.out.println("支付给华为云");
    }

    private void beforePay() {
        System.out.println("从农行取款");
    }
}
