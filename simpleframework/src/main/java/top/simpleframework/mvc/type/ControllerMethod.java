package top.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * ClassName: ControllerMethod
 * Description: 封装待执行的 Controller 及其方法实例和参数的映射
 * date: 2020/9/12 10:07
 *
 * @author crwen
 * @create 2020-09-12-10:07
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerMethod {
    // Controller 对应的 Class 对象
    private Class<?> controllerClass;
    // 执行的 Controller 方法实例
    private Method invokeMethod;
    // 方法参数名称以及对应的参数类型
    private Map<String, Class<?>> methodParameters;
}
