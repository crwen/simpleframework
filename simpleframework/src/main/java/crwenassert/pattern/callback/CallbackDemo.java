package crwenassert.pattern.callback;

/**
 * ClassName: CallbackDemo
 * Description:
 * date: 2020/8/24 12:32
 *
 * @author crwen
 * @create 2020-08-24-12:32
 * @since JDK 1.8
 */
public class CallbackDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() { // 回调方法
                System.out.println("我要休息啦");
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("我被回调了");
            }
        });
        thread.start();
    }
}
