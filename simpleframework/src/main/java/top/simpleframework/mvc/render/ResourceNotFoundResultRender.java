package top.simpleframework.mvc.render;

import top.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: ResourceNotFoundResultRender
 * Description: 资源找不到时使用的渲染器
 * date: 2020/9/11 23:23
 *
 * @author crwen
 * @create 2020-09-11-23:23
 * @since JDK 1.8
 */
public class ResourceNotFoundResultRender implements ResultRender{

    private String httpMethod;

    private String httpPath;

    public ResourceNotFoundResultRender(String requestMethod, String requestPath) {
        this.httpMethod = requestMethod;
        this.httpPath = requestPath;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND,
                "获取不到对应的请求资源：请求路径[" + httpPath + "] 请求方法[" + httpMethod + "]");
    }
}
