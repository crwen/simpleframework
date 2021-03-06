package crwenassert.pattern.proxy.impl;

import crwenassert.pattern.proxy.ToBPayment;

/**
 * ClassName: AlipayToB
 * Description:
 * date: 2020/9/4 17:08
 *
 * @author crwen
 * @create 2020-09-04-17:08
 * @since JDK 1.8
 */
public class AlipayToB implements ToBPayment {

    ToBPayment toBPayment;

    public AlipayToB(ToBPayment toBPayment) {
        this.toBPayment = toBPayment;
    }

    @Override
    public void pay() {
        beforePay();
        toBPayment.pay();
        afterPay();
    }

    private void afterPay() {
        System.out.println("支付给华为云");
    }

    private void beforePay() {
        System.out.println("从农行取款");
    }
}
