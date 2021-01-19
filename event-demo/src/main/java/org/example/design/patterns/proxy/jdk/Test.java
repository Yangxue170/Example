package org.example.design.patterns.proxy.jdk;

import org.example.design.patterns.proxy.StudentService;
import org.example.design.patterns.proxy.jdk.IStudentService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 14:37
 */
public class Test {
    public static void main(String[] args) {
        //1. 创建真实对象
        final StudentService studentService = new StudentService();
        //2. 创建动态代理
        /*
        三个参数：
            1. 类加载器：真实对象.getClass().getClassLoader()
            2. 接口数组：真实对象.getClass().getInterfaces()
            3. 处理器：new InvocationHandler()，处理和被代理对象的方法，即方法增强的地方
        */
        IStudentService proxyInstance = (IStudentService) Proxy.newProxyInstance(studentService.getClass().getClassLoader(),
                studentService.getClass().getInterfaces(), new InvocationHandler() {
                    /*代理逻辑编写的方法，代理对象调用的所有方法都会触发该方法的执行
                    参数：
                        1. proxy：代理对象（一般不用）
                        2. method：代理对象调用的方法，被封装为的对象
                        3. args：代理对象调用的方法时，传入的实际参数
                    */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println(method.getName() + "方法调用前");
                        //具体的方法执行
                        method.invoke(studentService, args);
                        System.out.println(method.getName() + "方法调用后");
                        return null;
                    }
                });
        //用户付钱
        proxyInstance.insertStudent();
        System.out.println("---------------------");
        //展示商品
        proxyInstance.deleteStudent();
    }

}
