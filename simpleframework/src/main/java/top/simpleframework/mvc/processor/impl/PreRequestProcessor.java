package top.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import top.simpleframework.mvc.RequestProcessorChain;
import top.simpleframework.mvc.processor.RequestProcessor;

/**
 * ClassName: PreRequestProcessor
 * Description: 请求预处理，包括编码以及路径处理
 * date: 2020/9/11 22:02
 *
 * @author crwen
 * @create 2020-09-11-22:02
 * @since JDK 1.8
 */
@Slf4j
public class PreRequestProcessor implements RequestProcessor {

    /**
     *  对请求进行统一的 UTF-8 编码，并取出路径的最后的斜杠
     * @param requestProcessorChain
     * @return
     * @throws Exception
     */
    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        // 1. 设置请求的编码
        requestProcessorChain.getRequest().setCharacterEncoding("UTF-8");
        // 2. 去除请求路径末尾的斜杠，方便后续适配 Controller
        // 比如 /aaa/bbb = /aaa/bbb
        String requestPath = requestProcessorChain.getRequestPath();
        if (requestPath.length() > 1 && requestPath.endsWith("/")) {
            String substring = requestPath.substring(0, requestPath.length() - 1);
            requestProcessorChain.setRequestPath(substring);
        }
        log.info("preprocessor request {} {}",requestProcessorChain.getRequestMethod(),requestProcessorChain.getRequestPath());

        return true;
    }
}
