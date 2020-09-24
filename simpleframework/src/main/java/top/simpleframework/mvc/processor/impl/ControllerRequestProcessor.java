package top.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import top.simpleframework.core.BeanContainer;
import top.simpleframework.mvc.RequestProcessorChain;
import top.simpleframework.mvc.annotation.RequestMapping;
import top.simpleframework.mvc.annotation.RequestParam;
import top.simpleframework.mvc.annotation.ResponseBody;
import top.simpleframework.mvc.processor.RequestProcessor;
import top.simpleframework.mvc.render.JsonResultRender;
import top.simpleframework.mvc.render.ResourceNotFoundResultRender;
import top.simpleframework.mvc.render.ResultRender;
import top.simpleframework.mvc.render.ViewResultRender;
import top.simpleframework.mvc.type.ControllerMethod;
import top.simpleframework.mvc.type.RequestPathInfo;
import top.simpleframework.util.ConvertUtil;
import top.simpleframework.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: ControllerRequestProcessor
 * Description: Controller 请求处理器
 * date: 2020/9/11 22:31
 *
 * @author crwen
 * @create 2020-09-11-22:31
 * @since JDK 1.8
 */
@Slf4j
public class ControllerRequestProcessor implements RequestProcessor {

    // IOC 容器
    BeanContainer beanContainer;
    // 请求和 controller 方法的映射集合
    private Map<RequestPathInfo, ControllerMethod> pathControllerMethodMap = new ConcurrentHashMap<>();

    /**
     *  依靠容器的能力，建立起请求路径、请求方法与 Controller 方法实例的映射
     */
    public ControllerRequestProcessor() {
        this.beanContainer = BeanContainer.getInstance();
        Set<Class<?>> requestMappingSet = beanContainer.getClassesByAnnotation(RequestMapping.class);
        initPathControllerMethodMap(requestMappingSet);
    }

    private void initPathControllerMethodMap(Set<Class<?>> requestMappingSet) {
        if (ValidationUtil.isEmpty(requestMappingSet)) {
            return;
        }
        // 1. 遍历所有被 @RequestMapping 标记的类，获取类上面该注解的属性值作为一级路径
        for (Class<?> requestMappingClass : requestMappingSet) {
            RequestMapping requestMapping = requestMappingClass.getAnnotation(RequestMapping.class);
            String basePath = requestMapping.value();
            if (!basePath.startsWith("/")) {
                basePath = "/" + basePath;
            }
            // 2. 遍历类里所有被 @RequestMapping 标记的方法，获取方法上面该注解的属性值，作为二级路径
            Method[] methods = requestMappingClass.getDeclaredMethods();
            if (ValidationUtil.isEmpty(methods)) {
                continue;
            }
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping methodRequest = method.getAnnotation(RequestMapping.class);
                    String methodPath = methodRequest.value();
                    if (!methodPath.startsWith("/")) {
                        methodPath = "/" + basePath;
                    }
                    String url = basePath + methodPath;

                    // 3. 解析方法里被 @RequestParam 标记的参数
                    Map<String, Class<?>> methodParams = parseMethodParma(method);

                    // 4. 将获取到的信息封装成 RequestPathInfo 实例和 ControllerMethod 实例，放置到映射表里
                    String httpMethod = String.valueOf(methodRequest.method());
                    RequestPathInfo requestPathInfo = new RequestPathInfo(httpMethod, url);
                    if (this.pathControllerMethodMap.containsKey(requestMapping)) {
                        log.warn("duplicate url: {} registration, current class {} method {} will override the former one",
                                requestPathInfo.getHttpPath(), requestMappingClass.getName(), method.getName());

                    }
                    ControllerMethod controllerMethod = new ControllerMethod(requestMappingClass, method, methodParams);
                    pathControllerMethodMap.put(requestPathInfo, controllerMethod);
                }
            }
        }
    }

    /**
     * 解析方法里被 @RequestParam 标记的参数
     * @param method
     * @return
     */
    private Map<String, Class<?>> parseMethodParma(Method method) {
        Map<String, Class<?>> methodParams = new HashMap<>();
        Parameter[] parameters = method.getParameters();
        if (ValidationUtil.isEmpty(parameters)) {
            return methodParams;
        }
        for (Parameter parameter : parameters) {
            RequestParam param = parameter.getAnnotation(RequestParam.class);
            // 目前暂定为 Controller 方法里面所有的参数都需要 @RequestParam 注解
            if (param == null) {
                throw new RuntimeException("The parameter must have @RequestParam");
            }
            // 获取该注解的属性值，作为参数名
            // 获取被标记的参数的数据类型，建立参数名与参数类型的映射
            methodParams.put(param.value(), parameter.getType());
        }
        return methodParams;
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        // 1. 解析 HttpServletRequest 的请求方法，请求路径，获取对应的 ControllerMethod
        String requestMethod = requestProcessorChain.getRequestMethod();
        String requestPath = requestProcessorChain.getRequestPath();
        ControllerMethod controllerMethod = this.pathControllerMethodMap.get(new RequestPathInfo(requestMethod, requestPath));
        if (controllerMethod == null) {
            requestProcessorChain.setResultRender(new ResourceNotFoundResultRender(requestMethod, requestPath));
            return false;
        }
        // 2. 解析请求参数，并传递给获取到的 ControllerMethod 实例去执行
        Object result = invokeControllerMethod(controllerMethod, requestProcessorChain.getRequest());
        // 3. 根据处理的结果，选择对应的 render 进行渲染
        setResultRender(result, controllerMethod, requestProcessorChain);
        return true;
    }

    /**
     *  根据不同情况设置不同的渲染器
     * @param result
     * @param controllerMethod
     * @param requestProcessorChain
     */
    private void setResultRender(Object result, ControllerMethod controllerMethod, RequestProcessorChain requestProcessorChain) {
        if (result == null) {
            return;
        }
        ResultRender resultRender;
        if (controllerMethod.getInvokeMethod().isAnnotationPresent(ResponseBody.class)) {
            resultRender = new JsonResultRender(result);
        } else {
            resultRender = new ViewResultRender(result);
        }
        requestProcessorChain.setResultRender(resultRender);
    }

    /**
     * 解析请求参数，并传递给获取到的 ControllerMethod 实例去执行
     * @param controllerMethod
     * @param request
     * @return
     */
    private Object invokeControllerMethod(ControllerMethod controllerMethod, HttpServletRequest request) {
        // 1. 从请求里获取 GET 或者 POST 的参数名及其对应的值
        Map<String, String> requestParamMap = new HashMap<>();
        // GET POST 方法的请求参数获取方式
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
            if (!ValidationUtil.isEmpty(parameter.getValue())) {
                // 只支持一个参数对应一个值的形式
                requestParamMap.put(parameter.getKey(), parameter.getValue()[0]);
            }
            
        }
        // 2. 根据获取到的请求参数名及其对应的值，以及 controllerMethod 里面的参数和类型的映射关系，去实例化出方法对应的参数
        List<Object> methodParams = new ArrayList<>();
        Map<String, Class<?>> methodParameterMap = controllerMethod.getMethodParameters();
        for (String paramName : methodParameterMap.keySet()) {
            Class<?> type = methodParameterMap.get(paramName);
            String requestValue = requestParamMap.get(paramName);
            Object value;
            // 只支持 String 以及基础类型 char int short byte double long float boolean
            if (requestValue == null) {
                // 将请求里的参数准更换程是培育参数类型的空值
                value = ConvertUtil.primitiveNull(type);
            } else {
                value = ConvertUtil.convert(type, requestValue);
            }
            methodParams.add(value);
        }
        // 3. 执行 Controller 里面对应的方法并返回结果
        Object controller = beanContainer.getBean(controllerMethod.getControllerClass());
        Method invokeMethod = controllerMethod.getInvokeMethod();
        invokeMethod.setAccessible(true);
        Object result ;
        try {
            if (methodParams.size() == 0) {
                result = invokeMethod.invoke(controller);
            } else {
                result = invokeMethod.invoke(controller, methodParams.toArray());
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getTargetException());
        }
        return result;
    }
}
