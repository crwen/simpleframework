package crwenassert.pattern.factory.abstractf;

import crwenassert.pattern.factory.entity.Keyboard;
import crwenassert.pattern.factory.entity.Mouse;

/**
 * ClassName: ComputerFactory
 * Description:
 * date: 2020/8/1 17:55
 *
 * @author crwen
 * @create 2020-08-01-17:55
 * @since JDK 1.8
 */
public interface ComputerFactory {
    Mouse createMouse();
    Keyboard createKeyboard();
}
