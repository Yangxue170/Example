package org.example.deadlock;

import java.util.Date;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/15 14:27
 */
public class LockTest {
    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public static void main(String[] args) {
        LockA la = new LockA();
        new Thread(la).start();
        LockB lb = new LockB();
        new Thread(lb).start();
    }


}
