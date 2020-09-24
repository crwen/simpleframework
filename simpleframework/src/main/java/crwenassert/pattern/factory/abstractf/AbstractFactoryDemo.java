package crwenassert.pattern.factory.abstractf;

import crwenassert.pattern.factory.entity.Keyboard;
import crwenassert.pattern.factory.entity.Mouse;

/**
 * ClassName: AbstractFactoryDemo
 * Description:
 * date: 2020/8/1 17:58
 *
 * @author crwen
 * @create 2020-08-01-17:58
 * @since JDK 1.8
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        ComputerFactory computerFactory = new HpComputerFactory();
        Mouse mouse = computerFactory.createMouse();
        Keyboard keyboard = computerFactory.createKeyboard();
        mouse.sayHello();
        keyboard.sayHi();
    }
}
