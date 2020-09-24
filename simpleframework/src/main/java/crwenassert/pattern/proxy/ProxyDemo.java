package crwenassert.pattern.proxy;

import crwenassert.pattern.proxy.impl.ToBPaymentImpl;
import crwenassert.pattern.proxy.impl.ToCPaymentImpl;
import crwenassert.pattern.proxy.jdk.AlipayInvocationHandler;
import crwenassert.pattern.proxy.jdk.JdkDynamicProxyUtil;

/**
 * ClassName: ProxyDemo
 * Description:
 * date: 2020/9/4 16:20
 *
 * @author crwen
 * @create 2020-09-04-16:20
 * @since JDK 1.8
 */
public class ProxyDemo {
    public static void main(String[] args) {
        //AlipayToC proxy = new AlipayToC(new ToCPaymentImpl());
        //proxy.pay();
        //ToBPayment toBPayment = new AlipayToB(new ToBPaymentImpl());
        //toBPayment.pay();

        ToCPayment toCPayment = new ToCPaymentImpl();
        AlipayInvocationHandler handler = new AlipayInvocationHandler(toCPayment);
        ToCPayment toCProxy = JdkDynamicProxyUtil.newProxyInstance(toCPayment, handler);
        toCProxy.pay();

        ToBPayment toBPayment = new ToBPaymentImpl();
        AlipayInvocationHandler handlerToB = new AlipayInvocationHandler(toBPayment);
        ToBPayment toBProxy = JdkDynamicProxyUtil.newProxyInstance(toBPayment, handlerToB);
        toBProxy.pay();
    }
}
