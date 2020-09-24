package crwenassert.pattern.proxy.impl;

import crwenassert.pattern.proxy.ToCPayment;

/**
 * ClassName: ToCPaymentImpl
 * Description:
 * date: 2020/9/4 16:17
 *
 * @author crwen
 * @create 2020-09-04-16:17
 * @since JDK 1.8
 */
public class ToCPaymentImpl implements ToCPayment {

    @Override
    public void pay() {
        System.out.println("以用户的名利进行支付");
    }
}
