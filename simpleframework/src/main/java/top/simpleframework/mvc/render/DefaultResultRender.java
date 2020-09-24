package top.simpleframework.mvc.render;

import top.simpleframework.mvc.RequestProcessorChain;

/**
 * ClassName: DefaultResultRender
 * Description: 默认渲染器
 * date: 2020/9/11 23:18
 *
 * @author crwen
 * @create 2020-09-11-23:18
 * @since JDK 1.8
 */
public class DefaultResultRender implements ResultRender{

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        requestProcessorChain.getResponse()
                .setStatus(requestProcessorChain.getResponseCode());
    }
}
