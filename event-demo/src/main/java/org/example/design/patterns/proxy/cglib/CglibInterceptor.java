package org.example.design.patterns.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.example.design.patterns.proxy.StudentService;

import java.lang.reflect.Method;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 15:20
 */
public class CglibInterceptor implements MethodInterceptor {
    /**
     * 执行被代理对象的任何方法都会经过该方法
     *
     * @param o           代理对象
     * @param method      被代理的对象方法
     * @param objects     以上三个参数和基于接口的动态代理中invoke方法的参数是一样的
     * @param methodProxy 当前执行方法的代理对象
     * @return 调用方法的返回值
     * @throws Throwable 异常¬
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("======插入后置通知======");
        return object;
    }

    public static void main(String[] args) {
        // 创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        // 为代理类指定需要代理的类，也即是父类
        enhancer.setSuperclass(StudentService.class);
        // 获取动态代理类对象并返回
        enhancer.setCallback(new CglibInterceptor());
        // 创建代理对象
        StudentService proxy = (StudentService) enhancer.create();
        // 通过代理对象调用目标方法
        proxy.insertStudent();
        proxy.deleteStudent();
    }
}
