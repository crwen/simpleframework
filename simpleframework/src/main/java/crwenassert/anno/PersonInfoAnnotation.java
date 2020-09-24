package crwenassert.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: PersonInfoAnnotation
 * Description:
 * date: 2020/8/1 20:11
 *
 * @author crwen
 * @create 2020-08-01-20:11
 * @since JDK 1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfoAnnotation {
    public String name();
    int age() default 19;
    String gender() default "male";
    String[] language();
}
