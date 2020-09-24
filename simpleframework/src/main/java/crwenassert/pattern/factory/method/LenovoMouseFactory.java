package crwenassert.pattern.factory.method;

import crwenassert.pattern.factory.entity.LenovoMouse;
import crwenassert.pattern.factory.entity.Mouse;

/**
 * ClassName: HpMouseFactory
 * Description:
 * date: 2020/8/1 14:49
 *
 * @author crwen
 * @create 2020-08-01-14:49
 * @since JDK 1.8
 */
public class LenovoMouseFactory implements MouseFactory {

    public Mouse createMouse() {
        return new LenovoMouse();
    }
}
