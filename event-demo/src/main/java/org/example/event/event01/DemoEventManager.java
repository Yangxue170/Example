package org.example.event.event01;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/14 11:10
 */
public class DemoEventManager {
    private Collection listeners;

    /**
     * 添加事件
     *
     * @param listener
     *   DoorListener
     */
    public void addDoorListener(DemoListener listener) {
        if (listeners == null) {
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    /**
     * 移除事件
     *
     * @param listener
     *   DoorListener
     */
    public void removeDoorListener(DemoListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    /**
     * 触发开门事件
     */
    protected void fireWorkspaceOpened() {
        if (listeners == null)
            return;
        DemoEvent event = new DemoEvent(this, "open");
        notifyListeners(event);
    }

    /**
     * 触发关门事件
     */
    protected void fireWorkspaceClosed() {
        if (listeners == null)
            return;
        DemoEvent event = new DemoEvent(this, "close");
        notifyListeners(event);
    }

    /**
     * 通知所有的DoorListener
     */
    private void notifyListeners(DemoEvent event) {
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            DemoListener listener = (DemoListener) iter.next();
            listener.doorEvent(event);
        }
    }

}
