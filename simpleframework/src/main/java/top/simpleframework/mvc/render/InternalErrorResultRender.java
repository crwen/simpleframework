package top.simpleframework.mvc.render;

import top.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: InternalErrorResultRender
 * Description: 内部异常渲染器
 * date: 2020/9/11 23:22
 *
 * @author crwen
 * @create 2020-09-11-23:22
 * @since JDK 1.8
 */
public class InternalErrorResultRender implements ResultRender{

    private String errorMsg;

    public InternalErrorResultRender(String errorMsg) {
        System.out.println("init error");
        this.errorMsg = errorMsg;
    }
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg);
    }
}
