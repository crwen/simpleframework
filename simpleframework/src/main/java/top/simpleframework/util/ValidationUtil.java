package top.simpleframework.util;

import java.util.Collection;
import java.util.Map;

/**
 * ClassName: ValidationUtil
 * Description:
 * date: 2020/8/2 16:39
 *
 * @author crwen
 * @create 2020-08-02-16:39
 * @since JDK 1.8
 */
public class ValidationUtil {

    /**
     *  String是否为 null 或 ""
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     *  Array 是否为 null 或者 size 为 0
     * @param objects
     * @return
     */
    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    /**
     *  集合是否为null 或 size 为 0
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     *  Map 是否为空或 size 为 0
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
