package crwenassert.pattern.factory.abstractf;

import crwenassert.pattern.factory.entity.DellKeyboard;
import crwenassert.pattern.factory.entity.DellMouse;
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
public class DellComputerFactory implements ComputerFactory {
    public Mouse createMouse() {
        return new DellMouse();
    }

    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}
