package org.example.design.patterns.proxy.jdk;

import org.example.design.patterns.proxy.StudentService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 12:28
 */
public class StudentInvocationHandler implements InvocationHandler {
    private IStudentService studentService;

    public StudentInvocationHandler(IStudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 代理逻辑编写的方法，代理对象调用的所有方法都会触发该方法的执行
     * 参数：
     *
     * @param proxy  代理对象（一般不用）
     * @param method 代理对象调用的方法，被封装为的对象
     * @param args   代理对象调用的方法时，传入的实际参数
     * @return 对象
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "方法调用前");
        //具体的方法执行
        method.invoke(studentService, args);
        System.out.println(method.getName() + "方法调用后");
        return null;
    }

    public static void main(String[] args) {
        //1. 创建真实对象
        IStudentService studentService = new StudentService();
        //方法被增强的地方
        InvocationHandler studentInvocationHandler = new StudentInvocationHandler(studentService);
        //2. 创建动态代理
        /*
        三个参数：
            1. 类加载器：真实对象.getClass().getClassLoader()
            2. 接口数组：真实对象.getClass().getInterfaces()
            3. 处理器：new InvocationHandler()，处理和被代理对象的方法，即方法增强的地方
        */
        IStudentService studentServiceProxy = (IStudentService) Proxy.newProxyInstance(studentService.getClass().getClassLoader(), studentService.getClass().getInterfaces(), studentInvocationHandler);
        studentServiceProxy.insertStudent();
        studentServiceProxy.deleteStudent();
    }

}
