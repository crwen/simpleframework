package top.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: RequestPathInfo
 * Description: 存储 http 请求路径和请求方法
 * date: 2020/9/12 10:11
 *
 * @author crwen
 * @create 2020-09-12-10:11
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPathInfo {
    // http 请求方法
    private String httpMethod;
    // http 请求路径
    private String httpPath;
}
