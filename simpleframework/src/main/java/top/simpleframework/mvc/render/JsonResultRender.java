package top.simpleframework.mvc.render;

import com.google.gson.Gson;
import top.simpleframework.mvc.RequestProcessorChain;

import java.io.PrintWriter;

/**
 * ClassName: JsonResultRender
 * Description: Json 渲染器
 * date: 2020/9/11 23:19
 *
 * @author crwen
 * @create 2020-09-11-23:19
 * @since JDK 1.8
 */
public class JsonResultRender implements ResultRender{

    private Object jsonData;

    public JsonResultRender(Object result) {
        this.jsonData = result;
    }

    @Override
    public void render(RequestProcessorChain requestProcessorChain) throws Exception {
        // 设置响应头
        requestProcessorChain.getResponse().setContentType("application/json");
        requestProcessorChain.getResponse().setCharacterEncoding("UTF-8");
        // 向响应流写入经过 gson 格式化之后的处理结果

        try (PrintWriter writer = requestProcessorChain.getResponse().getWriter();){
            Gson gson = new Gson();
            writer.write(gson.toJson(jsonData));
            writer.flush();
        }


    }
}
