### 标签logger

[参考文档地址](https://alanli7991.github.io/2016/10/20/slf4j%E5%92%8Clogback%E7%9A%84%E5%85%B3%E7%B3%BB%E5%92%8C%E9%85%8D%E7%BD%AE%E5%8E%9F%E7%90%86/)

[Learn](https://www.cnblogs.com/xrq730/p/8628945.html)
> <logger>元素对应了应用中通过LoggerFactory.getLogger()获取到的日志工具。

<logger>用来设置某一个包或者具体的某一个类的日志打印级别、以及指定<appender>。
<logger>仅有一个name属性，一个可选的level和一个可选的addtivity属性。
- name:用来指定受此logger约束的某一个包或者具体的某一个类。
- level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF，
- 还有一个特俗值INHERITED或者同义词NULL，代表强制执行上级的级别。
如果未设置此属性，那么当前logger将会继承上级的级别。
- addtivity:是否向上级logger传递打印信息。默认是true。

#### 测试Slf4j
> 首先给出结论：slf4j只是一个日志标准，并不是日志系统的具体实现。

具体的case示例
```
我们自己的系统中使用了logback这个日志系统
我们的系统使用了A.jar，A.jar中使用的日志系统为log4j
我们的系统又使用了B.jar，B.jar中使用的日志系统为slf4j-simple
这样，我们的系统就不得不同时支持并维护logback、log4j、slf4j-simple三种日志框架，非常不便。

slf4j-simple、logback都是slf4j的具体实现，log4j并不直接实现slf4j，但是有专门的一层桥接slf4j-log4j12来实现slf4j。
```

slf4j只做两件事情：
* 提供日志接口
* 提供获取具体日志对象的方法

### slf4j用法
常年不变一句话：Logger logger = LoggerFactory.getLogger(Object.class);
> 与@Slf4j的关系








