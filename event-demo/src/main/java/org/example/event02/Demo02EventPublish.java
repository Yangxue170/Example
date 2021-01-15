package org.example.event02;

import java.util.*;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/14 11:34
 */
public class Demo02EventPublish {

    private Collection listeners;

    /**
     * 添加事件
     *
     * @param listener DoorListener
     */
    public void addAllListener(EventListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    /**
     * 发布事件
     */
    public void publish(Demo02Event event) {
        System.out.println("=======事件发布中=========");
        notifyListeners(event);
    }

    /**
     * 通知所有的DoorListener
     */
    private void notifyListeners(Demo02Event event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            DemoEvent02Listener listener = (DemoEvent02Listener) iter.next();
            listener.listener(event);
        }
    }
}
