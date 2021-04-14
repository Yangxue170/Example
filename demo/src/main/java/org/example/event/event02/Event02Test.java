package org.example.event.event02;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/14 11:44
 */
public class Event02Test {
    public static void main(String[] args) {
        Demo02EventPublish publish = new Demo02EventPublish();
        publish.addAllListener(new DemoEvent02Listener());
        publish.publish(new Demo02Event(200));
    }
}
