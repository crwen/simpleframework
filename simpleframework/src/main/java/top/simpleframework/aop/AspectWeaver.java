package top.simpleframework.aop;

import top.simpleframework.aop.annotation.Aspect;
import top.simpleframework.aop.annotation.Order;
import top.simpleframework.aop.aspect.AspectInfo;
import top.simpleframework.aop.aspect.DefaultAspect;
import top.simpleframework.core.BeanContainer;
import top.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ClassName: AspectWeaver
 * Description:
 * date: 2020/9/9 9:54
 *
 * @author crwen
 * @create 2020-09-09-9:54
 * @since JDK 1.8
 */
public class AspectWeaver {
    private BeanContainer beanContainer;

    public AspectWeaver() {
        this.beanContainer = BeanContainer.getInstance();
    }

    public void doAop() {
        // 1. 获取所有的切面类
        Set<Class<?>> aspectSet = beanContainer.getClassesByAnnotation(Aspect.class);
        // 2. 拼装 AspectInfoList
        if (ValidationUtil.isEmpty(aspectSet))
            return;
        List<AspectInfo> aspectInfoList = packAspectInfoList(aspectSet);
        // 3. 遍历容器里的类
        Set<Class<?>> classSet = beanContainer.getClasses();
        for (Class<?> targetClass : classSet) {
            // 排除 AspectClass 自身
            if (targetClass.isAnnotationPresent(Aspect.class) ) {
                continue;
            }
            // 4. 粗筛符合调价你的 Aspect
            List<AspectInfo> roughMatchedAspectList = collectRoughMatchedAspectListForSpecificClass(aspectInfoList, targetClass);
            // 5. 尝试进行 Aspect 的织入
            wrapIfNecessary(roughMatchedAspectList,targetClass);
        }


    }

    /**
     * 尝试进行 Aspect 的织入
     * @param roughMatchedAspectList
     * @param targetClass
     */
    private void wrapIfNecessary(List<AspectInfo> roughMatchedAspectList, Class<?> targetClass) {
        // 创建动态代理对象
        AspectListExecutor executor = new AspectListExecutor(targetClass, roughMatchedAspectList);
        Object proxyBean = ProxyCreator.createProxy(targetClass, executor);
        // 替换被代理的对象
        beanContainer.addBean(targetClass, proxyBean);
    }

    /**
     *  粗筛符合条件的 Aspect
     * @param aspectInfoList
     * @param targetClass
     * @return
     */
    private List<AspectInfo> collectRoughMatchedAspectListForSpecificClass(List<AspectInfo> aspectInfoList, Class<?> targetClass) {
        List<AspectInfo> roughMatchedAspectList = new ArrayList<>();
        for (AspectInfo aspectInfo : aspectInfoList) {
            if (aspectInfo.getPointcutLocator().roughMatches(targetClass)) {
                roughMatchedAspectList.add(aspectInfo);
            }
        }
        return roughMatchedAspectList;
    }

    /**
     *  根据获取到的所有切面类拼接 AspectInfoList
     * @param aspectSet
     * @return
     */
    private List<AspectInfo> packAspectInfoList(Set<Class<?>> aspectSet) {
        List<AspectInfo> aspectInfoList = new ArrayList<>();
        for (Class<?> aspectClass : aspectSet) {
            if (verifyAspect(aspectClass)) {
                // 1. 获取 aspectClass 中的 order 和 Aspect 标签中的属性
                Order orderTag = aspectClass.getAnnotation(Order.class);
                Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
                // 2. 创建 PointcutLocator
                PointcutLocator pointcutLocator = new PointcutLocator(aspectTag.pointcut());
                // 3. 从容器中获取切面类实例
                DefaultAspect aspect = (DefaultAspect) beanContainer.getBean(aspectClass);
                // 4. 创建 AspectInfo 封装类
                AspectInfo aspectInfo = new AspectInfo(orderTag.value(), aspect, pointcutLocator);
                aspectInfoList.add(aspectInfo);
            } else {
                throw new RuntimeException("@Aspect and @Order have not been added to the Aspect class, " +
                        "or Aspect class does not extend from DefaultAspect");
            }
        }
        return aspectInfoList;
    }

    private void weaveByCategory(Class<? extends Annotation> category, List<AspectInfo> aspectInfoList) {
        // 1. 获取被代理类的集合
        Set<Class<?>> classSet = beanContainer.getClassesByAnnotation(category);
        // 2. 遍历被代理类，分别为每个被代理类生成动态代理实例
        if (ValidationUtil.isEmpty(classSet)) {
            return;
        }
        for (Class<?> targetClass : classSet) {
            AspectListExecutor aspectListExecutor = new AspectListExecutor(targetClass, aspectInfoList);
            Object proxyBean = ProxyCreator.createProxy(targetClass, aspectListExecutor);
            // 3. 将动态代理对象实例添加到容器里，取代未被代理前的类实例
            beanContainer.addBean(targetClass, proxyBean);
        }
    }

    /**
     *
     * @param categorizedMap
     * @param aspectClass
     */
    //private void categorizedAspect(Map<Class<? extends Annotation>, List<AspectInfo>> categorizedMap, Class<?> aspectClass) {
    //    Aspect aspectTag = aspectClass.getAnnotation(Aspect.class);
    //    Order orderTag = aspectClass.getAnnotation(Order.class);
    //    DefaultAspect aspect = (DefaultAspect) beanContainer.getBean(aspectClass);
    //    AspectInfo aspectInfo = new AspectInfo(orderTag.value(), aspect);
    //    if (!categorizedMap.containsKey(aspectTag.value())) {
    //        // 如果织入的 joinpoint 第一次出现，则以该 joinpoint 为 key，以新创建的 List<AspectInfo> 为 value
    //        List<AspectInfo> aspectInfoList = new ArrayList<>();
    //        aspectInfoList.add(aspectInfo);
    //        categorizedMap.put(aspectTag.value(), aspectInfoList);
    //    } else {
    //        // 如果织入的 joinpont 不是第一次出现，则往 joinpoint 对应的 value 里添加新的 Aspect 逻辑
    //        List<AspectInfo> aspectInfoList = categorizedMap.get(aspectTag.value());
    //        aspectInfoList.add(aspectInfo);
    //        //categorizedMap.put(aspectTag.value(), aspectInfoList);
    //    }
    //}

    /**
     *  验证框架中的 Aspect 类是否满足要求
     *      1. 要遵守给 Aspect 类添加 @Aspect 和 @Order 标签的规范
     *      2. 必须继承自 DefaultAspect
     *      3. 此 @Aspect 的属性值不能是它本身
     * @param aspectClass
     * @return
     */
    private boolean verifyAspect(Class<?> aspectClass) {
        return aspectClass.isAnnotationPresent(Aspect.class) &&
                aspectClass.isAnnotationPresent(Order.class) &&
                DefaultAspect.class.isAssignableFrom(aspectClass);
                //&&
                //aspectClass.getAnnotation(Aspect.class).value() != Aspect.class;
    }
}
