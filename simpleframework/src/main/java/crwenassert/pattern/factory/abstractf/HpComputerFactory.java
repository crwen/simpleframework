package crwenassert.pattern.factory.abstractf;

import crwenassert.pattern.factory.entity.HpKeyboard;
import crwenassert.pattern.factory.entity.HpMouse;
import crwenassert.pattern.factory.entity.Keyboard;
import crwenassert.pattern.factory.entity.Mouse;

/**
 * ClassName: DellComputerFactory
 * Description:
 * date: 2020/8/1 17:56
 *
 * @author crwen
 * @create 2020-08-01-17:56
 * @since JDK 1.8
 */
public class HpComputerFactory implements ComputerFactory {
    public Mouse createMouse() {
        return new HpMouse();
    }

    public Keyboard createKeyboard() {
        return new HpKeyboard();
    }
}
