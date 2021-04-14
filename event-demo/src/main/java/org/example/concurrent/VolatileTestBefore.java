package org.example.concurrent;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/2/5 15:21
 */
public class VolatileTestBefore {
    public static volatile int race = 0;

    private static final int THREADS_COUNT = 20;

    public static void increase() {
        race++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                    System.out.println("---------run-------");
                }
            });
            //start() 方法是启动线程，让线程变成就绪状态等待 CPU 调度后执行。并不是直接开始运行，等待cpu空闲
            threads[i].start();
            System.out.println("---------start-------");

        }

        //返回当前线程的线程组中活动线程的数量
        //TODO 在此处产生了死循环
        while (Thread.activeCount() > 1) {
            Thread.currentThread().getThreadGroup().list();
            Thread.yield();
            //Thread yield()方法的作用是暂停当前线程，以便其他线程有机会执行，
            // 不过不能指定暂停的时间，并且也不能保证当前线程马上停止。
            // yield方法只是将Running状态转变为Runnable状态。
            System.out.println("---------yield-------");
        }

        System.out.println(race);
    }
}

/*
 * 使用：Thread.currentThread().getThreadGroup().list();
 * 查看当前线程情况
 * java.lang.ThreadGroup[name=main,maxpri=10]
 * Thread[main,5,main]  //main方法线程
 * Thread[Monitor Ctrl-Break,5,main]  //Monitor Ctrl-Break线程
 */

