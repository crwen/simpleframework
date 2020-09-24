package crwenassert.anno;

import java.lang.annotation.Annotation;

/**
 * ClassName: AnnotatinoDemo
 * Description:
 * date: 2020/8/1 20:19
 *
 * @author crwen
 * @create 2020-08-01-20:19
 * @since JDK 1.8
 */
public class AnnotatinoDemo {
    public static void main(String[] args) throws Exception {
        Course course = new Course();
        course.getCourseInfo();
        Class<?> clazz = Class.forName("crwenassert.anno.CourseInfoAnnotation");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {

        }
    }
}
