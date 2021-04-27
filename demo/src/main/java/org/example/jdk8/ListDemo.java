package org.example.jdk8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/27 12:17
 */
public class ListDemo {

    public static void main(String[] args) {
//        salveMethodOne();
//        salveMethodTwo();
//        salveMethodThree();
        salveMethodFour();
    }


    /**
     * 单线程操作，正常
     */
    private static void sigleThred() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        for (String element : list) {
            System.out.println(element);
        }
    }

    /**
     * 多线程操作，报错
     * Exception in thread "Thread-12" Exception in  thread "Thread-16" Exception in thread "Thread-20"
     * java.util.ConcurrentModificationException （并发修改的异常）
     */
    private static void moreThread() {
        List<String> list = new ArrayList<>();

        for (long i = 0; i < 30; i++) {
            new Thread(() -> {

                list.add("hello");
                list.add("world");
                list.add("java");
                System.out.println(list);

            }).start();
        }
    }

    private static void salveMethodOne() {
        Vector<String> vector = new Vector<>();
        for (long i = 0; i < 30; i++) {
            new Thread(() -> {

                vector.add("hello");
                vector.add("world");
                vector.add("java");
                System.out.println(vector);

            }).start();
        }
    }

    private static void salveMethodTwo() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (long i = 0; i < 30; i++) {
            new Thread(() -> {

                list.add("hello");
                list.add("world");
                list.add("java");
                System.out.println(list);

            }).start();
        }
    }


    private static void salveMethodThree() {
        List<String> list = new CopyOnWriteArrayList<String>();
        for (long i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add("hello");
                list.add("world");
                list.add("java");
                System.out.println(list);

            }).start();
        }

    }

    private static void salveMethodFour() {
        ThreadLocal<List<String>> threadList = new ThreadLocal<List<String>>() {
            @Override
            protected List<String> initialValue() {
                return new ArrayList<>();
            }
        };
        //只对单个前线程有效，线程之间相互隔离
        for (long i = 0; i < 30; i++) {
            new Thread(() -> {
                threadList.get().add("hello");
                threadList.get().add("world");
                threadList.get().add("java");
                System.out.println(threadList.get());

            }).start();
        }

    }

}
