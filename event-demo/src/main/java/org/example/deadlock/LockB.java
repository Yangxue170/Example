package org.example.deadlock;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/15 14:29
 */

import java.util.Date;

public class LockB implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(new Date().toString() + " LockB 开始执行");
            while (true) {
                synchronized (LockTest.obj2) {
                    System.out.println(new Date().toString() + " LockB 锁住 obj2");
                    // 此处等待是给A能锁住机会
                    Thread.sleep(3000);
                    synchronized (LockTest.obj1) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj1");
                        // 为测试，占用了就不放
                        Thread.sleep(60 * 1000);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}