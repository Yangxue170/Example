package org.example.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/27 16:49
 */
public class AppProvider {
    public static void main(String[] args) throws IOException {
        //加载xml配置文件启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF.spring/dubbo-provider.xml");
        context.start();
        System.in.read();
        // 按任意键退出
    }
}