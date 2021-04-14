package org.example.map;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 18:17
 */
//public class MapDemo {
//    public static void main(String[] args) {
//        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
//// map默认capacity 16，当元素个数达到(capacity - capacity >> 2) = 12个时会触发rehash
//        for (int i = 0; i < 11; i++) {
//            map.put(i, i);
//        }
//        System.out.println(map);
//        Integer integer = map.computeIfAbsent(12, (k) -> {
//            // 这里会导致死循环 :(
//            map.put(100, 100);
//            return k;
//        });
//        System.out.println(integer);
//
//// 其他操作
//    }
//}
