package org.example.jdk8;

import java.util.function.Consumer;

/**
 * @author Jdx
 * @version 1.0
 * TODO  Java 8 的 Consumer、Supplier、Predicate和Function
 * @description 函数式编程-函数式接口Consumer,作用是消费一个数据。
 * @date 2021/4/20 18:15
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> consumer = System.out::println;
        //意为消费一个指定泛型的数据: accept
        consumer.accept("ces");

        //首先做一个操作， 然后再做一个操作，实现组合: andThen

    }
}
