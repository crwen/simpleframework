package crwenassert.pattern.eventmode;

/**
 * ClassName: EventModeDemo
 * Description:
 * date: 2020/8/24 12:43
 *
 * @author crwen
 * @create 2020-08-24-12:43
 * @since JDK 1.8
 */
public class EventModeDemo {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource();
        SingleClickEventListener singleClickEventListener = new SingleClickEventListener();
        DoubleClickEventListener doubleClickEventListener = new DoubleClickEventListener();
        Event event = new Event();
        event.setType("doubleclick");
        eventSource.register(singleClickEventListener);
        eventSource.register(doubleClickEventListener);
        eventSource.publishEvent(event);
    }
}
