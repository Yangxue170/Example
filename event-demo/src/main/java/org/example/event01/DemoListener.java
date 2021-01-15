package org.example.event01;

import java.util.EventListener;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/14 11:07
 */
public interface DemoListener extends EventListener {
    /**
     * @param event
     */
    void doorEvent(DemoEvent event);

}
