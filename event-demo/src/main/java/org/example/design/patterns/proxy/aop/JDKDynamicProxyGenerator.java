package org.example.design.patterns.proxy.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 15:42
 */
public class JDKDynamicProxyGenerator {

    public static Object generatorJDKProxy(final IUserService targetPoint, final IAspect aspect) {

        return Proxy.newProxyInstance(targetPoint.getClass().getClassLoader(),
                targetPoint.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 执行切面方法,对入参进行校验
                        boolean prepareAction = aspect.startTransaction(args);
                        if (prepareAction) {
                            // 具体逻辑代码执行,返回值为方法执行结果
                            Object result = method.invoke(targetPoint, args);
                            aspect.endTransaction();
                            return result;
                        } else {
                            throw new RuntimeException("args: " + Arrays.toString(args) + "不能为null ");
                        }
                    }
                });
    }


    public static void main(String[] args) throws Exception {
        System.out.println("无代理前 调用方法 userService.saveUser 输出......");
        IUserService userService = new UserService();
        userService.saveUser("zby", "1234567890");
        System.out.println("有代理后AOP 是怎么样的？ Proxy......");
        IUserService proxyUserService = (IUserService) JDKDynamicProxyGenerator.generatorJDKProxy(userService, new CustomAspect());
        proxyUserService.saveUser("zby", "1234567890");
        /* 制造异常,两个入参都是null   */
        proxyUserService.saveUser(null, null);
    }
}
