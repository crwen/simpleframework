package top.simpleframework.core;

import crwenassert.controller.frontend.MainPageController;
import crwenassert.service.solo.HeadLineService;
import crwenassert.service.solo.Impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import top.simpleframework.core.annotation.Controller;
import top.simpleframework.mvc.DispatcherServlet;

/**
 * ClassName: BeanContainerTest
 * Description:
 * date: 2020/8/2 16:46
 *
 * @author crwen
 * @create 2020-08-02-16:46
 * @since JDK 1.8
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @Order(1)
    @Test
    public void loadBeansTest() {
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("crwenassert");
        Assertions.assertEquals(6, beanContainer.size());
        Assertions.assertEquals(true, beanContainer.isLoaded());
        //System.out.println(beanContainer.size());
    }

    @Order(2)
    @DisplayName("根据类获取实例")
    @Test
    public void getBeanTest() {
        MainPageController controller = (MainPageController) beanContainer.getBean(MainPageController.class);
        //Object controller =  beanContainer.getBean(MainPageController.class);
        Assertions.assertEquals(true, controller instanceof MainPageController);
        DispatcherServlet dispatcherServlet = (DispatcherServlet) beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertEquals(null, dispatcherServlet);
    }

    @DisplayName("根据注解获取对应是咧")
    @Order(3)
    @Test
    public void getClassesByAnnotation() {
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(3, beanContainer.getClassesByAnnotation(Controller.class).size());
    }

    @DisplayName("根据接口获取实现类")
    @Order(4)
    @Test
    public void getClassesBySuperTest() {
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(true,
                beanContainer.getClassesBySuper(HeadLineService.class)
                        .contains(HeadLineServiceImpl.class));
    }

    @Test
    public void run() {
        //int port = 8080;
        //Tomcat tomcat = new Tomcat();
        //tomcat.setPort(port);
        //
        //// 添加 listener
        //StandardServer server = (StandardServer) tomcat.getServer();
        //AprLifecycleListener listener = new AprLifecycleListener();
        //server.addLifecycleListener(listener);
        //
        //// 设置 contextPath 和路径
        //String contextPath = "/simpleframework";
        //String docBase = new File("src/main/webapp").getAbsolutePath();
        //Context context = tomcat.addWebapp(contextPath, docBase);
        //System.out.println("添加 contextPath 和 docBase");
        //
        //String servletName = "hello";
        //String servletMapping = "/hello";
        //tomcat.addServlet(contextPath, servletName, new DispatcherServlet());
        //context.addServletMappingDecoded(servletMapping, servletName);
        //
        //// 启动 tomcat
        //try {
        //    tomcat.start();
        //    tomcat.getServer().await();
        //} catch (LifecycleException e) {
        //    e.printStackTrace();
        //}
    }
}