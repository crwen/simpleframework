package crwenassert.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: CourseInfoAnnotation
 * Description:
 * date: 2020/8/1 20:13
 *
 * @author crwen
 * @create 2020-08-01-20:13
 * @since JDK 1.8
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseInfoAnnotation {
    public String courseName();
    public String courseTag();
    public String courseProfile();
    public int courseIndex() default 404;
}
