package top.simpleframework.mvc.render;

import top.simpleframework.mvc.RequestProcessorChain;

/**
 * ClassName: ResultRender
 * Description: 渲染请求结果
 * date: 2020/9/11 23:17
 *
 * @author crwen
 * @create 2020-09-11-23:17
 * @since JDK 1.8
 */
public interface ResultRender {
    void render(RequestProcessorChain requestProcessorChain) throws Exception;
}
