package top.simpleframework.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.simpleframework.mvc.processor.RequestProcessor;
import top.simpleframework.mvc.render.DefaultResultRender;
import top.simpleframework.mvc.render.InternalErrorResultRender;
import top.simpleframework.mvc.render.ResultRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * ClassName: RequestProcessorChain
 * Description:
 *      1. 以责任链的模式执行注册的请求处理器
 *      2. 委派给特定的 Render 实例对处理后的结果进行渲染
 * date: 2020/9/11 22:02
 *
 * @author crwen
 * @create 2020-09-11-22:02
 * @since JDK 1.8
 */
@Data
@Slf4j
public class RequestProcessorChain {

    // 请求处理器迭代器
    private Iterator<RequestProcessor> requestProcessorIterator;

    // 请求 request
    private HttpServletRequest request;

    // 请求 response
    private HttpServletResponse response;

    // http 请求方法
    private String requestMethod;

    // http 请求路径
    private String requestPath;

    // http 响应状态码
    private int responseCode;

    // 请求结果渲染器
    private ResultRender resultRender;

    public RequestProcessorChain(Iterator<RequestProcessor> iterator, HttpServletRequest req, HttpServletResponse resp) {
        this.requestProcessorIterator = iterator;
        this.request = req;
        this.response = resp;
        this.requestMethod = req.getMethod();
        this.requestPath = req.getPathInfo();
        this.responseCode = HttpServletResponse.SC_OK;
    }

    /**
     *  以责任链的模式执行请求链
     */
    public void doRequestProcessorChain() {
        // 1. 通过迭代器遍历注册的请求处理器实现类 列表
        try {
            while(requestProcessorIterator.hasNext()) {
                // 2. 直到某个请求处理器执行后返回为 false 为止
                if (!requestProcessorIterator.next().process(this)) {
                    break;
                }
            }
        } catch (Exception e) {
            // 3. 期间如果出现异常，则交由内部异常渲染处理器处理
            this.resultRender = new InternalErrorResultRender(e.getMessage());
            log.error("doRequestProcessorChain error: ", e);
        }


    }

    /**
     *  执行处理器
     */
    public void doRender() {
        // 1. 如果请求处理器实现类均未选择合适的渲染器，则使用默认的
        if (this.resultRender == null) {
            this.resultRender = new DefaultResultRender();
        }
        // 2. 调用渲染器的 render 方法对结果进行渲染
        try {
            this.resultRender.render(this);
        } catch (Exception e) {
            log.error("doRender error: ", e);
            throw new RuntimeException(e);
        }
    }
}
