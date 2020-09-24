package top.simpleframework.mvc.processor;

import top.simpleframework.mvc.RequestProcessorChain;

/**
 * ClassName: RequestProcessor
 * Description: 请求执行器
 * date: 2020/9/11 22:01
 *
 * @author crwen
 * @create 2020-09-11-22:01
 * @since JDK 1.8
 */
public interface RequestProcessor {

    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception;
}
