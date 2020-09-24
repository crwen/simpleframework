package top.simpleframework.inject;

import crwenassert.controller.frontend.MainPageController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.simpleframework.core.BeanContainer;

/**
 * ClassName: DependencyInjectorTest
 * Description:
 * date: 2020/8/2 19:45
 *
 * @author crwen
 * @create 2020-08-02-19:45
 * @since JDK 1.8
 */
class DependencyInjectorTest {

    @DisplayName("依赖注入 doIoc")
    @Test
    public void doIocTest() {
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("crwenassert");
        Assertions.assertEquals(true, beanContainer.isLoaded());
        MainPageController mainPageController = (MainPageController) beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, mainPageController instanceof MainPageController);
        Assertions.assertEquals(null, mainPageController.getHeadLineShopCategoryCombineService());
        new DependencyInjector().doIoc();
        Assertions.assertNotEquals(null, mainPageController.getHeadLineShopCategoryCombineService());

    }
}