package top.simpleframework.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName: ClassUtil
 * Description:
 * date: 2020/8/2 14:17
 *
 * @author crwen
 * @create 2020-08-02-14:17
 * @since JDK 1.8
 */
@Slf4j
public class ClassUtil {

    private static final String FILE_PROTOCOL = "file";

    /**
     *  获取包下类集合
     * @param packageName 包名
     * @return 类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        // 1. 获取类加载器，是为了获取项目发布的实际路径
        ClassLoader classLoader = getClassLoader();
        // 2. 通过类加载器获取到加载的资源
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url == null) {
            log.warn("unable to retrieve anything from package" + packageName);
            return null;
        }
        // 3. 依据不同的资源类型，采用不同的方式获取资源的集合
        Set<Class<?>> classSet = null;
        // 过滤处文件类型的资源
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            classSet = new HashSet<Class<?>>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        // TODO 针对其他类型资源的处理
        return classSet;
    }

    /**
     *  递归获取目标 package里面的所有 class 文件（包括目录下的 class 文件）
     * @param emptyClassSet
     * @param fileSource
     * @param packageName 报名
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) {
            return;
        }
        // 如果是文件夹，则掉调用 listFiles 方法获取文件夹下的文件/文件夹
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    // 获取文件的绝对值路径
                    String absoluteFilePath = file.getAbsolutePath();
                    if (absoluteFilePath.endsWith(".class")) {
                        // 如果是 class 文件，直接加载
                        addToClassSet(absoluteFilePath);
                    }
                }
                return false;
            }
            // 根据 class 文件的绝对路径，获取并生成 class 对象，并法如 classSet 中
            private void addToClassSet(String absoluteFilePath) {
                // 1. 从 class 文件的绝对值路径里提取出包含了 package 的类名
                absoluteFilePath = absoluteFilePath.replace(File.separator, ".");
                String className = absoluteFilePath.substring(absoluteFilePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                // 通过反射获取对象加到 classSet 里
                Class<?> targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });

        if (files != null) {
            for (File f : files) {
                // 递归调用
                extractClassFile(emptyClassSet, f, packageName);
            }
        }
    }

    /**
     *  获取 classloader
     * @return 当前 ClassLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static void main(String[] args) {
        extractPackageClass("top.simpleframework");
    }

    /**
     * 获取 Class 对象
     * @param className class名（pacakge + 类名）
     */
    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error: ", e);
            throw new RuntimeException();
        }
    }

    /**
     * 实例化 class
     * @param clazz Class
     * @param <T> class 的类型
     * @param <T> 是否支持创建出私有 class 对象的实例
     * @return 类的实例化
     */
    public static <T> T newInstance(Class<?> clazz, boolean accessible) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T) constructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     *  设置类的属性值
     * @param field 成员变量
     * @param target 类实例
     * @param value 成员变量的值
     * @param accessible 是否允许设置私有属性
     */
    public static void setField(Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("setField error", e);
            throw new RuntimeException(e);
        }
    }
}
