package top.simpleframework.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;

/**
 * ClassName: PointcutLocator
 * Description: 解析 Aspect 表达式，并定位被织入的目标
 * date: 2020/9/10 10:51
 *
 * @author crwen
 * @create 2020-09-10-10:51
 * @since JDK 1.8
 */
public class PointcutLocator {
    /**
     *  Pointcut 解析器，直接给它赋值上 Aspectj 的所有表达式，以便支持对众多表达式的解析
     */
    private PointcutParser pointcutParser = PointcutParser.
            getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(
                    PointcutParser.getAllSupportedPointcutPrimitives());

    /**
     *  表达式解析器
     */
    private PointcutExpression pointcutExpression;

    public PointcutLocator(String expression) {
        this.pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    /**
     *  判断传入的 Class 对象是否是 Aspect 的目标代理类，即匹配 Pointcut 表达式（初筛）
     * @param targetClass 目标类
     * @return 是否匹配
     */
    public boolean roughMatches(Class<?> targetClass) {
        // couldMatchJoinPointsInType 只能校验 within
        // 不能校验 executino call get set，面对无法校验的表达式，直接返回 true
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     *  判断传入的 Method 对象是否是 Aspect 的目标代理方法，即匹配 Pointcut 表达式（精筛）
     * @param method
     * @return
     */
    public boolean accurateMatches(Method method) {
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches() )
            return true;
        else
            return false;
    }
}
