package org.example.event01;

import java.util.EventObject;

/**
 * @author Jdx
 * @version 1.0
 * @description 事件主体
 * @date 2021/1/14 10:51
 */
public class DemoEvent extends EventObject {

    // 表示门的状态，有“开”和“关”两种
    private String doorState = "";

    public DemoEvent(Object source, String doorState) {
        super(source);
        this.doorState = doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }

    public String getDoorState() {
        return this.doorState;
    }
}