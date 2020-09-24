package top.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.simpleframework.aop.PointcutLocator;

/**
 * ClassName: AspectInfo
 * Description:
 * date: 2020/9/9 0:49
 *
 * @author crwen
 * @create 2020-09-09-0:49
 * @since JDK 1.8
 */
@AllArgsConstructor
@Getter
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspectObject;
    private PointcutLocator pointcutLocator;
}
