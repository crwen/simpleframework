package crwenassert.pattern.singleton;

/**
 * ClassName: StarvingSingleton
 * Description:
 * date: 2020/9/3 17:26
 *
 * @author crwen
 * @create 2020-09-03-17:26
 * @since JDK 1.8
 */
public class StarvingSingleton {
    private static final StarvingSingleton instance = new StarvingSingleton();
    private StarvingSingleton(){}

    public static StarvingSingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(StarvingSingleton.getInstance());
        System.out.println(StarvingSingleton.getInstance());
    }
}
