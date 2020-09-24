package top.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import top.simpleframework.mvc.RequestProcessorChain;
import top.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * ClassName: StaticResourceProcessor
 * Description: 资源请求处理，包括但不限于图片、css、以及js 文件
 * date: 2020/9/11 22:03
 *
 * @author crwen
 * @create 2020-09-11-22:03
 * @since JDK 1.8
 */
@Slf4j
public class StaticResourceRequestProcessor implements RequestProcessor {
    private RequestDispatcher defaultDispatcher;
    private final String Default_TOMCAT_SERVLET = "default";
    private final String STATIC_RESOURCE_PREFIX = "/static/";

    public StaticResourceRequestProcessor(ServletContext servletContext) {
        // 使用默认的处理器
        this.defaultDispatcher = servletContext.getNamedDispatcher(Default_TOMCAT_SERVLET);
        if (this.defaultDispatcher == null){
            throw new RuntimeException(" 获取 Tomcat 默认 servlet 失败 ");
        }
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        // 1. 通过请求路径判断是否时请求的静态资源 webapp/static
        if (isStaticResource(requestProcessorChain.getRequestPath())) {
            // 2. 如果时静态资源，则将请求转发给 default servlet 处理
            defaultDispatcher.forward(requestProcessorChain.getRequest(), requestProcessorChain.getResponse());
            return false;
        }

        return true;
    }

    /**
     *  判断请求的是不是静态资源
     * @param path
     * @return
     */
    private boolean isStaticResource(String path) {
        return path.startsWith(STATIC_RESOURCE_PREFIX);
    }
}
