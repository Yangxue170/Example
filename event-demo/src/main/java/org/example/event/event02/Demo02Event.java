package org.example.event.event02;

import java.util.EventObject;

/**
 * @author Jdx
 * @version 1.0
 * @description TODO 1、事件类对象
 * @date 2021/1/14 11:25
 */
public class Demo02Event extends EventObject {
    //1、继承eventObject类，表示这是个事件主体
    /**
     * 参数
     */
    private Integer price;


    /**
     * Constructs a prototypical Event.
     *
     * @throws IllegalArgumentException if source is null.
     */
    public Demo02Event(Integer price) {
        super("");
        this.price = price;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
