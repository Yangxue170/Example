package org.example.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/2/5 15:31
 */
public class VolatileTestAfter {

    public static volatile int race = 0;

    private static final int THREADS_COUNT = 20;

    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

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
                    countDownLatch.countDown();
                }
            });
            threads[i].start();
        }
        countDownLatch.await();
        //每次输出的结果都不相同
        System.out.println(race);
    }

    /**
     * 每次输出的结果都不相同，原因：
     * volatile只能保证可见性，无法保证原子性，而自增操作并不是一个原子操作
     *
     * 自增方法的字节码：
     *   public static void increase();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=0, args_size=0
     *          0: getstatic     #3                  // Field race:I
     *          3: iconst_1
     *          4: iadd
     *          5: putstatic     #3                  // Field race:I
     *          8: return
     *       LineNumberTable:
     *         line 20: 0
     *         line 21: 8
     *
     *
     *
     * 指令getstatic把race的值取到操作栈顶时，volatile关键字保证了race的值在此时是正确的。
     * 但是在执行iconst_1、iadd这些指令的时候，其他线程可能已经把race的值加大了，
     * 而在操作栈顶的值就变成了过期的数据，所以putstatic指令执行后就可能把较小的race值同步回主内存之中。
     *
     */

    /**
     * 解决方法1：在自增方法添加synchronized，将其变成原子操作。不足：每次自增加锁，性能较差
     * 解决方法2：保证运算的原子性可以使用java.util.concurrent.atomic包下的一些原子操作类。例如最常见的： AtomicInteger
     */
    private  static AtomicInteger i = new AtomicInteger(0);

    public static void addI() {
        //先取值再加1
        i.getAndIncrement();
    }

}
