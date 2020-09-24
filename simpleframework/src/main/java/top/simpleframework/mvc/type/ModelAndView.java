package top.simpleframework.mvc.type;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: ModelAndView
 * Description: 存储处理完后的结果数据以及显示该数据的视图
 * date: 2020/9/12 23:38
 *
 * @author crwen
 * @create 2020-09-12-23:38
 * @since JDK 1.8
 */
public class ModelAndView {
    // 页面所在路径
    @Getter
    private String view;
    // 页面的 data 数据
    @Getter
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView setView(String view) {
        this.view = view;
        return this;
    }

    public ModelAndView addViewData(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
        return this;
    }

}
