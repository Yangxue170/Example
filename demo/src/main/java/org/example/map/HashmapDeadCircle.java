package org.example.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * jdk7扩容时都可能导致死锁
 * jdk8在PutTreeValue时可能死循环   死循环在hashMap的1816行或2229行， java version "1.8.0_111"
 * jstack发现可能卡在 at java.util.HashMap$TreeNode.balanceInsertion(HashMap.java:2229)
 * 也有可能卡在  at java.util.HashMap$TreeNode.root(HashMap.java:1816)

 */
public class HashmapDeadCircle {
    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            HashMapThread hmt0 = new HashMapThread();
            hmt0.start();
        }
    }
    static class HashMapThread extends Thread {
        private static AtomicInteger ai = new AtomicInteger(0);
        private static Map<Integer, Integer> map = new HashMap<Integer, Integer>(1);

        @Override
        public void run() {
            while (ai.get() < 100000) {
                map.put(ai.get(), ai.get());
                ai.incrementAndGet();
            }
            System.out.println(Thread.currentThread().getName() + "执行结束完");
        }
    }
}
