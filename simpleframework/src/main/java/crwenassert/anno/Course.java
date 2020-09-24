package crwenassert.anno;

/**
 * ClassName: Course
 * Description:
 * date: 2020/8/1 20:14
 *
 * @author crwen
 * @create 2020-08-01-20:14
 * @since JDK 1.8
 */
@CourseInfoAnnotation(courseName = "play",
        courseTag = "Java", courseProfile = "offer")
public class Course {
    @PersonInfoAnnotation(name = "per", language = {"Java", "Go"})
    private String author;

    @CourseInfoAnnotation(courseName = "info", courseTag = "crs",
            courseProfile = "get offer")
    public void getCourseInfo( ) {

    }
}
