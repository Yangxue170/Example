package org.example.event01;

/**
 * @author Jdx
 * @version 1.0
 * @description 事件监听者，监听到事件进行处理（考虑只关心自己关心的事件）
 * @date 2021/1/14 10:52
 */
public class DemoEventListener implements DemoListener {
    @Override
    public void doorEvent(DemoEvent event) {
        // TODO Auto-generated method stub
        if (event.getDoorState() != null && event.getDoorState().equals("open")) {
            System.out.println("门1打开");
        } else {
            System.out.println("门1关闭");
        }
    }
}
