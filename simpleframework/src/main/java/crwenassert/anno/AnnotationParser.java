package crwenassert.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ClassName: AnnotationParser
 * Description:
 * date: 2020/8/1 20:34
 *
 * @author crwen
 * @create 2020-08-01-20:34
 * @since JDK 1.8
 */
public class AnnotationParser {
    // 解析类的注解
    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("crwenassert.anno.Course");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            CourseInfoAnnotation courseInfoAnnotation = (CourseInfoAnnotation) annotation;
            System.out.println("课程名: " + courseInfoAnnotation.courseName());
            System.out.println("课程标签：" + courseInfoAnnotation.courseTag());
            System.out.println("课程简介: " + courseInfoAnnotation.courseIndex());
        }
    }

    public static void parseFieldAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("crwenassert.anno.Course");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(PersonInfoAnnotation.class)) {
                PersonInfoAnnotation personInfoAnnotation = declaredField.getAnnotation(PersonInfoAnnotation.class);
                System.out.println(personInfoAnnotation.name());
                System.out.println(personInfoAnnotation.age());
                System.out.println(personInfoAnnotation.gender());

                System.out.println(Arrays.toString(personInfoAnnotation.language()));
            }
        }
    }

    public static void parseMethodAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("crwenassert.anno.Course");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(CourseInfoAnnotation.class)) {
                CourseInfoAnnotation courseInfoAnnotation = method.getAnnotation(CourseInfoAnnotation.class);
                System.out.println(courseInfoAnnotation.courseName());
                System.out.println(courseInfoAnnotation.courseTag());
                System.out.println(courseInfoAnnotation.courseProfile());

                System.out.println(courseInfoAnnotation.courseIndex());
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //parseTypeAnnotation();
        parseFieldAnnotation();
    }
}
