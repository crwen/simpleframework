package top.simpleframework.aop;

import crwenassert.controller.superadmin.HeadLineOperationController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.simpleframework.core.BeanContainer;
import top.simpleframework.inject.DependencyInjector;

/**
 * ClassName: AspectWeaverTest
 * Description:
 * date: 2020/9/9 10:49
 *
 * @author crwen
 * @create 2020-09-09-10:49
 * @since JDK 1.8
 */
public class AspectWeaverTest {

    @DisplayName("通用逻辑测试，AOP")
    @Test
    public void doAopTest() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("crwenassert");
        AspectWeaver aspectWeaver = new AspectWeaver();
        aspectWeaver.doAop();
        new DependencyInjector().doIoc();

        HeadLineOperationController bean = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        //bean.addHeadLine(null, null);
        //System.out.println(bean);
    }
}
