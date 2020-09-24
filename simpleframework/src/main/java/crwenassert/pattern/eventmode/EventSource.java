package crwenassert.pattern.eventmode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: EventSource
 * Description:
 * date: 2020/8/24 12:40
 *
 * @author crwen
 * @create 2020-08-24-12:40
 * @since JDK 1.8
 */
public class EventSource {
    private List<EventListener> listenerList = new ArrayList<>();
    public void register(EventListener listener) {
        listenerList.add(listener);
    }

    public void publishEvent( Event event ) {
        for (EventListener listener : listenerList) {
            listener.processEvent(event);
        }
    }
}
