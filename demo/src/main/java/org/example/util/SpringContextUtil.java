//package org.example.util;
//
//import org.springframework.context.ApplicationContext;
//
//import java.util.Objects;
//
///**
// * @author Jdx
// * @version 1.0
// * @description
// * @date 2021/5/10 14:56
// */
//public class SpringContextUtil { private static ApplicationContext applicationContext;
//
//    /**
//     * 获取上下文
//     *
//     * @return ApplicationContext
//     */
//    public static ApplicationContext getApplicationContext() {
//        return applicationContext;
//    }
//
//    /**
//     * 设置上下文
//     *
//     * @param applicationContext 上下文
//     */
//    public static void setApplicationContext(ApplicationContext applicationContext) {
//        SpringContextUtil.applicationContext = applicationContext;
//    }
//
//    /**
//     * 通过名字获取上下文中的bean
//     *
//     * @param name 名字
//     * @return bean
//     */
//    public static Object getBean(String name) {
//        Objects.requireNonNull(applicationContext);
//        return getApplicationContext().getBean(name);
//    }
//
//    /**
//     * 通过类型获取上下文中的bean
//     *
//     * @param clazz 类型
//     * @return T
//     */
//    public static <T> T getBean(Class<T> clazz) {
//        Objects.requireNonNull(applicationContext);
//        return getApplicationContext().getBean(clazz);
//    }
//
//    /**
//     * 通过名字与类型获取上下文中的bean
//     *
//     * @param name 名字
//     * @param clazz 类型
//     * @return T
//     */
//    public static <T> T getBean(String name, Class<T> clazz) {
//        Objects.requireNonNull(applicationContext);
//        return getApplicationContext().getBean(name, clazz);
//    }
//}
