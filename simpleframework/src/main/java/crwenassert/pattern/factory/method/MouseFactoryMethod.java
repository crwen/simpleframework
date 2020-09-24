package crwenassert.pattern.factory.method;

import crwenassert.pattern.factory.entity.Mouse;

/**
 * ClassName: MouseFactoryMethod
 * Description:
 * date: 2020/8/1 14:51
 *
 * @author crwen
 * @create 2020-08-01-14:51
 * @since JDK 1.8
 */
public class MouseFactoryMethod {
    public static void main(String[] args) {
        MouseFactory mouseFactory = new HpMouseFactory();
        Mouse mouse = mouseFactory.createMouse();
        mouse.sayHello();
    }
}
