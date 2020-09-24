package top.simpleframework.mvc;

import top.simpleframework.aop.AspectWeaver;
import top.simpleframework.core.BeanContainer;
import top.simpleframework.inject.DependencyInjector;
import top.simpleframework.mvc.processor.RequestProcessor;
import top.simpleframework.mvc.processor.impl.ControllerRequestProcessor;
import top.simpleframework.mvc.processor.impl.JspRequestProcessor;
import top.simpleframework.mvc.processor.impl.PreRequestProcessor;
import top.simpleframework.mvc.processor.impl.StaticResourceRequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: DispatcherServlet
 * Description:
 * date: 2020/7/31 21:26
 *
 * @author crwen
 * @create 2020-07-31-21:26
 * @since JDK 1.8
 */
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
    List<RequestProcessor> PROCESSOR = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        // 对容器进行初始化
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("crwenassert");
        new AspectWeaver().doAop();
        new DependencyInjector().doIoc();
        // 初始化请求处理器责任链
        PROCESSOR.add(new PreRequestProcessor());
        PROCESSOR.add(new StaticResourceRequestProcessor(getServletContext()));
        PROCESSOR.add(new JspRequestProcessor(getServletContext()));
        PROCESSOR.add(new ControllerRequestProcessor());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 创建责任链对象实例
        RequestProcessorChain requestProcessorChain = new RequestProcessorChain(PROCESSOR.iterator(), req, resp);
        // 2. 通过责任链模式来依次调用请求处理器对请求进行处理
        requestProcessorChain.doRequestProcessorChain();
        // 3. 对处理结果进行渲染
        requestProcessorChain.doRender();
    }
}
