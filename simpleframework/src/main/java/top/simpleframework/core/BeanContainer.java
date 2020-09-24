package top.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import top.simpleframework.aop.annotation.Aspect;
import top.simpleframework.core.annotation.Component;
import top.simpleframework.core.annotation.Controller;
import top.simpleframework.core.annotation.Repository;
import top.simpleframework.core.annotation.Service;
import top.simpleframework.util.ClassUtil;
import top.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: BeanContainer
 * Description:
 * date: 2020/8/2 16:12
 *
 * @author crwen
 * @create 2020-08-02-16:12
 * @since JDK 1.8
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    /**
     *  存放所有被配置标记的目标对象的 Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap();

    /**
     *  加载 bean 的注解列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION
            = Arrays.asList(Component.class, Controller.class, Repository.class, Service.class, Aspect.class);

    /**
     *  容器是否已经加载过 bean
     */
    private boolean loaded = false;

    public boolean isLoaded(){
        return loaded;
    }

    /**
     *  Bean 实例数量
     * @return
     */
    public int size() {
        return beanMap.size();
    }

    /**
     *  获取 Bean 容器实例
     * @return BeanContainer
     */
    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;
        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    /**
     *  扫描加载所有 Bean
     * @param packageName
     */
    public synchronized void loadBeans(String packageName) {
        // 判断 bean 容器是否被加载过
        if (isLoaded()) {
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("extract nothing from packageName" + packageName);
            return;
        }

        for (Class<?> clazz :classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                // 如果类上面标记了定义的注解
                if (clazz.isAnnotationPresent(annotation)) {
                    beanMap.put(clazz,ClassUtil. newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
     *  添加一个 class 对象及其 Bean 实例
     * @param clazz Class 对象
     * @param bean Bean 实例
     * @return 原有的 Bean 实例，没有范湖 null
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     *  移除一个 IOC 容器管理的对象
     * @param clazz Class 对象
     * @return 删除的 Bean 实例，没有则返回 null
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据 Class 对象获取 Bean 实例
     * @param clazz Class 对象
     * @return Bean 实例
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     *  获取容器管理的所有 Class 对象集合
     * @return
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     *  获取所有 Bean 集合
     * @return
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     *  根据注解筛选出 Bean 的 Class 集合
     * @param annotation 注解
     * @return Class 集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        // 1. 获取 beanMap 的所有 class 对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }
        // 2. 通过注解筛选被注解标记的 class 对象
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
     *  通过接口或者父类获取实现类的 Class 集合，不包括其本身
     * @param interfaceOrClass
     * @return
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        // 1. 获取 beanMap 所有 class 对象
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }
        // 2. 判断 keySet 里的元素是否是传入的接口或者类的子类
        Set<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)) {
                classSet.add(clazz);
            }
        }

        return classSet.size() > 0 ? classSet : null;
    }

}
