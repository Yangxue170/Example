package org.example.dubbo;

import org.example.dubbo.provider.ProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/27 16:46
 */
public class AppConsumer {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/META-INF.spring/dubbo-consumer.xml");
        context.start();
        ProviderService providerService = (ProviderService) context.getBean("providerService");
        String str = providerService.sayHello("hello");
        System.out.println(str);
        System.in.read();
    }

}
