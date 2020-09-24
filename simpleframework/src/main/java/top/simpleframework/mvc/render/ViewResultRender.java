package top.simpleframework.mvc.render;

import top.simpleframework.mvc.RequestProcessorChain;
import top.simpleframework.mvc.type.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * ClassName: ViewResultRender
 * Description: 页面渲染器
 * date: 2020/9/11 23:20
 *
 * @author crwen
 * @create 2020-09-11-23:20
 * @since JDK 1.8
 */
public class ViewResultRender implements ResultRender {

    private static final String VIEW_PATH = "/templates/";
    private ModelAndView modelAndView;

    /**
     *  对传入的参数进行处理，并赋值给 ModelAndView 成员变量
     * @param mv
     */
    public ViewResultRender(Object mv) {
        // 1. 如果入参类型是 ModelAndView ，直接赋值给成员变量
        if (mv instanceof ModelAndView) {
            this.modelAndView = (ModelAndView) mv;
        } else if (mv.getClass() == String.class) {
            // 2. 如果入参类型是 String，则为视图，需要包装后才能赋值给成员变量
            this.modelAndView = new ModelAndView();
            modelAndView.setView(String.valueOf(mv));
        } else {
            // 3. 针对其他情况，直接抛出异常

            throw new RuntimeException("illegal request result type");
        }
    }

    /**
     *  将请求处理结果按照视图路径转发至对应视图进行展示
     * @param requestProcessorChain
     * @throws Exception
     */
    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        HttpServletResponse response = requestProcessorChain.getResponse();
        HttpServletRequest request = requestProcessorChain.getRequest();
        String view = modelAndView.getView();
        Map<String, Object> model = modelAndView.getModel();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        // jsp
        request.getRequestDispatcher(VIEW_PATH + view).forward(request, response);
    }
}
