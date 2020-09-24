package top.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import top.simpleframework.core.BeanContainer;
import top.simpleframework.inject.annotation.Autowired;
import top.simpleframework.util.ClassUtil;
import top.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * ClassName: DependencyInjector
 * Description:
 * date: 2020/8/2 18:02
 *
 * @author crwen
 * @create 2020-08-02-18:02
 * @since JDK 1.8
 */
@Slf4j
public class DependencyInjector {

    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     *  执行 Ioc
     */
    public void doIoc() {
        // 1. 遍历 Bean 容器中所有的 Class 对象
        if (ValidationUtil.isEmpty(beanContainer.getClasses())) {
            log.warn("empty classset in BeanContainer");
            return;
        }
        for (Class<?> clazz : beanContainer.getClasses()) {
            // 2. 遍历 Class 对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                // 3. 找出被 Autowired 标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    // 4. 获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    // 5. 获取这些成员变量的类型在容器里对应的实例
                    Object filedValue = getFieldInstance(fieldClass, autowiredValue);
                    if (filedValue == null) {
                        throw new RuntimeException("unable to inject relevant type, target fieldClass is:"
                                + fieldClass.getName() + "autowiredValue is " + autowiredValue);
                    } else {
                        // 6. 通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, filedValue, true);
                    }

                }
            }
        }
    }

    /**
     *  根据 Class 在 beanContainer 里获取其实例或实现类
     * @param fieldClass
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue != null) {
            return fieldValue;
        } else {
            Class<?> implementedClass = getImplementClass(fieldClass, autowiredValue);
            if (implementedClass != null) {
                return beanContainer.getBean(implementedClass);
            } else {
                return null;
            }
        }
    }

    /**
     *  获取接口实现类
     * @param fieldClass
     * @return
     */
    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)) {
            if (ValidationUtil.isEmpty(autowiredValue)) {
                if (classSet.size() == 1) {
                    return classSet.iterator().next();
                } else {
                    throw new RuntimeException("multiple implemented classes for "
                            + fieldClass.getName() + " please set @Autowired's value to pick one");
                }
            } else {
                for (Class<?> clazz : classSet) {
                    if (autowiredValue.equals(clazz.getSimpleName())) {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
