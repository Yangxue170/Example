package org.example.event02;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/14 11:31
 */
public class DemoEvent02Listener implements EventListener {
    /**
     * 监听事件
     */
    public void listener(Demo02Event event) {
        System.out.println("----------事件监听----------" + event.getPrice());
    }
}
