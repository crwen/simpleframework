package crwenassert.pattern.eventmode;

/**
 * ClassName: SingleClickEventListener
 * Description:
 * date: 2020/8/24 12:38
 *
 * @author crwen
 * @create 2020-08-24-12:38
 * @since JDK 1.8
 */
public class SingleClickEventListener implements EventListener {

    @Override
    public void processEvent(Event event) {
        if ("singleclick".equals(event.getType())) {
            System.out.println("单击被触发了");
        }
    }
}
