package crwenassert.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassName: LazyDoubleCheckSingleton
 * Description:
 * date: 2020/9/3 17:29
 *
 * @author crwen
 * @create 2020-09-03-17:29
 * @since JDK 1.8
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {}

    public static LazyDoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    // memory = allocate(); // 分配对象内存
                    // instance(memory) 初始化对象
                    // instance = memory; // 设置 instance 指向分配的内存
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        Class<LazyDoubleCheckSingleton> clazz = LazyDoubleCheckSingleton.class;
        try {
            Constructor<LazyDoubleCheckSingleton> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            System.out.println(constructor.newInstance());
            System.out.println(LazyDoubleCheckSingleton.getInstance());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
