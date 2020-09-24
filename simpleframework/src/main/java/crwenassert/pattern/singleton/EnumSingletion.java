package crwenassert.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassName: EnumSingletion
 * Description:
 * date: 2020/9/3 17:37
 *
 * @author crwen
 * @create 2020-09-03-17:37
 * @since JDK 1.8
 */
//jad
public class EnumSingletion {

    private EnumSingletion() {}

    private enum ContainerHolder {
        HOLDER;
        private EnumSingletion instance;
        private ContainerHolder() {
            instance = new EnumSingletion();
        }
    }

    public static EnumSingletion getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    public static void main(String[] args) {
        Class<EnumSingletion> clazz = EnumSingletion.class;
        try {
            Constructor<EnumSingletion> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            EnumSingletion enumSingletion = constructor.newInstance();
            System.out.println(enumSingletion.getInstance());
            System.out.println(EnumSingletion.getInstance());

            //Class cla = ContainerHolder.class;
            //Constructor constructor1 = cla.getDeclaredConstructor(String.class, int.class);
            //constructor1.setAccessible(true);
            //System.out.println(constructor1.newInstance());
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
