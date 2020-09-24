package top.simpleframework.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * ClassName: ClassUtilTest
 * Description:
 * date: 2020/8/2 15:03
 *
 * @author crwen
 * @create 2020-08-02-15:03
 * @since JDK 1.8
 */
class ClassUtilTest {


    @DisplayName("提取目标类方法：extractPackageClass")
    @Test
    public void extractPackageClassTest() {
        Set<Class<?>> classSet = ClassUtil.extractPackageClass("crwenassert.entity");
        Assertions.assertNotEquals(0, classSet.size());
        for (Class<?> clazz : classSet) {
            System.out.println(clazz);
        }
    }
}