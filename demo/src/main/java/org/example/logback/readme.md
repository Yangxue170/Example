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





