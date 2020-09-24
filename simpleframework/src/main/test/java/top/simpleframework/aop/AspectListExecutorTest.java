package top.simpleframework.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import top.simpleframework.aop.aspect.AspectInfo;
import top.simpleframework.aop.mock.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: AspectListExecutorTest
 * Description:
 * date: 2020/9/9 9:18
 *
 * @author crwen
 * @create 2020-09-09-9:18
 * @since JDK 1.8
 */
class AspectListExecutorTest {

    @DisplayName("")
    @Test
    public void sortTest() {
        List<AspectInfo> aspectInfoList = new ArrayList<>();
        aspectInfoList.add(new AspectInfo(3, new Mock1(), null));
        aspectInfoList.add(new AspectInfo(5, new Mock2(), null));
        aspectInfoList.add(new AspectInfo(2, new Mock3(), null));
        aspectInfoList.add(new AspectInfo(4, new Mock4(), null));
        aspectInfoList.add(new AspectInfo(1, new Mock5(), null));

        AspectListExecutor aspectListExecutor = new AspectListExecutor(AspectListExecutor.class, aspectInfoList);
        List<AspectInfo> sortedAspectInfoList = aspectListExecutor.getSortedAspectInfoList();
        for (AspectInfo aspectInfo : sortedAspectInfoList) {
            System.out.println(aspectInfo.getOrderIndex() + " " + aspectInfo.getAspectObject());
        }

    }

}