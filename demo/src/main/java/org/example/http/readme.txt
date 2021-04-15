1、自定义拦截器
    1、继承ClientHttpRequestInterceptor，重写 intercept方法，在此方法内
        不仅可以重新设置request的参数，
        同时也可以执行方法 response = execution.execute(request, body);
    2、把自定义的Interceptor添加到RestTemplate
        restTemplate.setInterceptors(Collections.singletonList(interceptor));
    3、使用RestTemplate请求外部接口

2、@PostConstruct 注解
    该注解并不是Spring的注解，而是java自己的注解(javax.annotation)，
    作用：
    在项目启动时需要加载某个方法的时候，可以使用@Component+@PostConstruct方法将一个方法完成初始化操作。
    用来修饰一个非静态的void（）方法，并且被修饰的方法不能抛出异常。该方法会在依赖注入完成之后被自动调用，只调用一次。

    该注解的方法在整个Bean初始化中的执行顺序：
    Constructor(构造方法) -> @Autowired(依赖注入) -> @PostConstruct(注释的方法)

3、拦截器加载的时间点在Spring Context之前,可以使用@Bean注解提前去加载.否则会有注入失败的问题。

4、