package crwenassert.pattern.proxy.impl;

import crwenassert.pattern.proxy.ToCPayment;

/**
 * ClassName: AlipayToC
 * Description:
 * date: 2020/9/4 16:17
 *
 * @author crwen
 * @create 2020-09-04-16:17
 * @since JDK 1.8
 */
public class AlipayToC implements ToCPayment {

    ToCPayment toCPayment;

    public AlipayToC(ToCPayment toCPayment) {
        this.toCPayment = toCPayment;
    }

    @Override
    public void pay() {
        beforePay();
        toCPayment.pay();
        afterPay();
    }

    private void afterPay() {
        System.out.println("支付给华为云");
    }

    private void beforePay() {
        System.out.println("从农行取款");
    }
}
