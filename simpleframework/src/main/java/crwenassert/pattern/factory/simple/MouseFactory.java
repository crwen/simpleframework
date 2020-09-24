package crwenassert.pattern.factory.simple;

import crwenassert.pattern.factory.entity.DellMouse;
import crwenassert.pattern.factory.entity.HpMouse;
import crwenassert.pattern.factory.entity.Mouse;

/**
 * ClassName: MouseFactory
 * Description:
 * date: 2020/8/1 14:32
 *
 * @author crwen
 * @create 2020-08-01-14:32
 * @since JDK 1.8
 */
public class MouseFactory {
    // 对创建对象加工
    // 违反开闭原则
    public static Mouse createMouse(String mouse) {
        if (mouse.equalsIgnoreCase("Dell")) {
            return new DellMouse();
        } else if (mouse.equalsIgnoreCase("HP")) {
            return new HpMouse();
        }
        throw new IllegalArgumentException("非法参数");
    }

    public static void main(String[] args) {
        Mouse mouse = MouseFactory.createMouse("hp");
        mouse.sayHello();
    }
}
