package top.simpleframework.mvc.processor.impl;

import top.simpleframework.mvc.RequestProcessorChain;
import top.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * ClassName: JspRequestProcessor
 * Description: jsp 资源请求处理
 * date: 2020/9/11 22:30
 *
 * @author crwen
 * @create 2020-09-11-22:30
 * @since JDK 1.8
 */
public class JspRequestProcessor implements RequestProcessor {

    // jsp 请求的 RequestDispatcher 的名称
    private static final String JSP_SERVLET = "jsp";
    // jsp 请求资源路径前缀
    private static final String JSP_RESOURCE_PREFIX = "/templates/";

    /**
     *  jsp 的 RequestDispatcher，处理 jsp 资源
     */
    private RequestDispatcher jspServlet;

    public JspRequestProcessor(ServletContext servletContext) {
        jspServlet = servletContext.getNamedDispatcher(JSP_SERVLET);
        if (null == jspServlet) {
            throw new RuntimeException("there is no jsp servlet");
        }
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        if (isJspResource(requestProcessorChain.getRequestPath())) {
            jspServlet.forward(requestProcessorChain.getRequest(), requestProcessorChain.getResponse());
            return false;
        }
        return true;
    }

    /**
     *  请求是否是 jsp 资源
     * @param url
     * @return
     */
    private boolean isJspResource(String url) {
        return url.startsWith(JSP_RESOURCE_PREFIX);
    }
}
