package org.example.event.event01;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/14 11:11
 */
public class EventTest {

    /**
     * 这种方式的实现有个问题，对于listener只想处理特定的event时不太合适
     * 所有listener都监听到该事件
     */
    public static void main(String[] args) {
        DemoEventManager manager = new DemoEventManager();
        // 给门1增加监听器
        manager.addDoorListener(new DemoEventListener());
        // 给门2增加监听器
        manager.addDoorListener(new DemoEventListenerTwo());
        // 开门
        manager.fireWorkspaceOpened();
        System.out.println("我已经进来了");
        // 关门
        manager.fireWorkspaceClosed();
    }

}
