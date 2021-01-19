# 代理模式

> 核心是：在调用者与被调用者中间增加一个中介的角色，也就是代理。

> 代理的内容是: 对象.

>本质：代理类其实是在之前类的基础上做了一层封装。 

#### 为什么需要代理模式

简单理解**可以在不修改代理对象代码的基础上，通过扩展代理类，进行一些功能的附加与增强。**

AOP切面、事物 都是通过代理实现，对原对象进行增强处理。==对扩展开放、对修改封闭。==



#### 代理模式的基本原理？

java中有==静态代理、JDK动态代理、CGLib动态代理==的方式。静态代理指的是代理类是在编译期就存在的，相反动态代理则是在程序运行期动态生成的。

1. 静态代理：为每个类创建代理类，使得代码复杂且不好维护。

   > case：对于记录日志的操作，每个类都定义代理类，每个方法的前后都追加日志，操作繁琐。

   对于静态代理的问题，应运而生了动态代理！

2.  动态代理：在程序运行期间，动态的为被代理对象创建代理类（使用反射机制）。（==Question： 如何判定那些是被代理对象。==）

### 动态代理：

#### JDK动态代理

> 基于接口的动态代理

- 特点：字节码随用随创建，随用随加载
- 作用：不修改源码的基础上对方法增强

#####  实现步骤：

1. 代理对象和真实对象实现相同的接口
2. 代理对象 = Proxy.newProxyInstance();
3. 使用代理对象调用方法。
4. 增强方法

```java
//接口定义
public interface IStudentService {
    void insertStudent();
    void deleteStudent();
}
```



```java
//要被代理的对象 ； 实现接口
public class StudentService implements IStudentService {
    @Override
    public void insertStudent() {
        System.out.println("准备添加学生");
        //do something 添加学生
        System.out.println("添加学生成功");

    }

    @Override
    public void deleteStudent() {
        System.out.println("准备删除学生");
        //do something 删除学生
        System.out.println("删除学生成功");
    }
}
```

为真实对象创建代理对象（代理对象调用的所有方法都会触发invoke() 方法的执行）

```java
// 方式一：在调用地方直接创建代理类，调用方法。
public class Test {
    public static void main(String[] args) {
        //1. 创建真实对象-被代理对象
        final StudentService studentService = new StudentService();
        //2. 创建动态代理-代理对象
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
        //添加学生
        proxyInstance.insertStudent();
        System.out.println("---------------------");
        //删除学生
        proxyInstance.deleteStudent();
    }
}
```

使用方式不同：推荐(更清晰)

```java
//方式二：创建独立的handler类

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
```



创建代理对象的方法：java.lang.reflect.Proxy#newProxyInstance

```java
// 第二个参数需要的参数是被代理类的实现接口。

public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces, 
                                          InvocationHandler h)//处理器
```

##### 不足

> JDK的动态代理也存在不足，即被代理类必须要有实现的接口，如没有接口则无法使用动态代理（从newProxyInstance方法的第二个参数可得知，必须传入被代理类的实现接口），那么需要使用CGLib动态代理。



#### CGLIB动态代理

> CGLib代理是功能最为强大的一种代理方式，因为其不仅解决了静态代理需要创建多个代理类的问题，还解决了JDK代理需要被代理对象实现某个接口的问题。



> Cglib是针对类来实现代理的，他的原理是对代理的目标类生成一个子类，并覆盖其中方法实现增强，因为底层是基于创建被代理类的一个子类，所以它避免了JDK动态代理类的缺陷。但因为采用的是继承，所以不能对final修饰的类进行代理。final修饰的类不可继承。



#### 细节

- ==基于子类的动态代理==(基于继承)：

  - 涉及的类：Enhancer
  - 提供者：第三方cglib库

- 如何创建代理对象：

- 使用Enhancer类中的create方法

- 创建代理对象的要求：

- 被代理类不能是最终类（final修饰）

- create方法的参数：

  - Class：字节码

    它是用于指定被代理对象的字节码。

  - Callback：用于提供增强的代码

    它是让我们写如何代理。我们一般都是些一个该接口的实现类，通常情况下都是匿名内部类，但不是必须的。此接口的实现类都是谁用谁写。**我们一般写的都是该接口的子接口实现类：MethodInterceptor**



Maven 依赖：

```java
      <dependency>
          <groupId>cglib</groupId>
          <artifactId>cglib</artifactId>
          <version>3.2.7</version>
      </dependency>
```

自定义处理：实现 net.sf.cglib.proxy.MethodInterceptor#intercept

```java
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
```



#### Cglib 总结

- CGlib可以传入接口也可以传入普通的类，接口使用实现的方式,普通类使用会使用继承的方式生成代理类.
- 由于是继承方式,如果是 static方法,private方法,final方法等描述的方法是不能被代理的
- 做了方法访问优化，使用建立方法索引的方式避免了传统JDK动态代理需要通过Method方法反射调用.
- 提供callback 和filter设计，可以灵活地给不同的方法绑定不同的callback。编码更方便灵活。
- CGLIB会默认代理Object中equals,toString,hashCode,clone等方法。比JDK代理多了clone。



#### 静态代理与动态代理的区别？

静态代理： 编译时生成代理类。

动态代理：运行时根据需要动态生成代理类数据信息。

#### 动态代理在java程序中的应用？

> Spring AOP默认是使用jdk动态代理，但是也支持使用cglib动态代理。

