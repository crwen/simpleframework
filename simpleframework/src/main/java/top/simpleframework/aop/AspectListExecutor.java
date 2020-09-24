package top.simpleframework.aop;

import lombok.Getter;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import top.simpleframework.aop.aspect.AspectInfo;
import top.simpleframework.aop.aspect.DefaultAspect;
import top.simpleframework.util.ValidationUtil;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: AspectListExecutor
 * Description:
 * date: 2020/9/9 0:19
 *
 * @author crwen
 * @create 2020-09-09-0:19
 * @since JDK 1.8
 */
public class AspectListExecutor implements MethodInterceptor {

    // 被代理的类
    private Class<?> targetClass;
    // 排好序的 aspect 列表
    @Getter
    private List<AspectInfo> sortedAspectInfoList;

    public AspectListExecutor(Class<?> targetClass, List<AspectInfo> aspectList) {
        this.targetClass = targetClass;
        this.sortedAspectInfoList = sortAspectInfoList(aspectList);
    }

    /**
     *  按照 order 的值进行升序排序，确保 order 值小的 aspect 先被织入
     * @param aspectList
     * @return
     */
    private List<AspectInfo> sortAspectInfoList(List<AspectInfo> aspectList) {
        Collections.sort(aspectList, new Comparator<AspectInfo>() {
            @Override
            public int compare(AspectInfo o1, AspectInfo o2) {
                // 按照值得大小进行升序排序
                return o1.getOrderIndex() - o2.getOrderIndex();
            }
        });
        return aspectList;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object returnValue = null;
        // 精筛
        collectAccurateMatchedAspectList(method);
        if (ValidationUtil.isEmpty(sortedAspectInfoList)) {
            returnValue = proxy.invokeSuper(obj, args);
            return returnValue;
        }
        // 1. 按照 order 的顺序升序执行完所有 Aspect 的 before 方法
        invokeBeforeAdvices(method, args);
        // 2. 执行被代理类的方法
        try {
            // 3. 如果被代理方法正确返回，按照 order 的顺序降序执行完所有 Aspect 的 afterReturning 方法
            returnValue = proxy.invokeSuper(obj, args);
            returnValue = invokeAfterReturningAdvices(method, args, returnValue);

        } catch (Exception e) {
            // 4. 如果被代理方法抛出异常，则按照 order 的顺序降序执行完所有 Aspect 的 afterThrowing 方法
            invokeAfterThrowingAdvices(method, args, e);
        }

        return returnValue;
    }

    private void collectAccurateMatchedAspectList(Method method) {
        if (ValidationUtil.isEmpty(sortedAspectInfoList)) {
            return ;
        }
        Iterator<AspectInfo> iterator = sortedAspectInfoList.iterator();
        while (iterator.hasNext()) {
            AspectInfo aspectInfo = iterator.next();
            if (! aspectInfo.getPointcutLocator().accurateMatches(method)) {
                iterator.remove();
            }
        }
    }

    private void invokeAfterThrowingAdvices(Method method, Object[] args, Exception e) throws Throwable {
        for (int i = sortedAspectInfoList.size() - 1; i >= 0 ; i--) {
            sortedAspectInfoList.get(i).getAspectObject()
                    .afterThrowing(targetClass, method, args, e);
        }
    }

    private Object invokeAfterReturningAdvices(Method method, Object[] args, Object returnValue) throws Throwable {
        Object result = null;
        for (int i = sortedAspectInfoList.size() - 1; i >= 0 ; i--) {
            result = sortedAspectInfoList.get(i).getAspectObject()
                    .afterReturning(targetClass, method, args, returnValue);
        }
        return result;
    }

    private void invokeBeforeAdvices(Method method, Object[] args) throws Throwable {
        for (AspectInfo aspectInfo : sortedAspectInfoList) {
            DefaultAspect aspectObject = aspectInfo.getAspectObject();
            aspectObject.before(targetClass, method, args);
        }
    }
}
