
请求参数：http://localhost:8080/my/hello?date=2019-03-27%2000:00:00

1、Date作为输入/输出参数格式问题
    问题1、前端传递过来的时间参数通常是String类型的。后端的实体类中一般定义为Date类型。这时候需要做一个String类型到Date类型的映射。
        异常：异常如下——-
            Failed to convert value of type 'java.lang.String' to required type 'java.util.Date';
            nested exception is org.springframework.core.convert.ConversionFailedException:
            Failed to convert from type [java.lang.String] to type [java.util.Date]
            for value '2019-03-27 00:00:00'; nested exception is java.lang.IllegalArgumentException

    解决方案：
        1、利用spring框架中的Converter接口实现自定义Bean
            org.example.convert.DateConvert
        2、直接在相关的字段上使用 JsonFormat 注解。
            @JsonFormat(pattern = “yyyy-MM-dd HH:mm:ss”, timezone = “GMT+8”)
            private Date time;

    问题2：后端的Date类型直接返回给前端是以时间戳形式返回的，如果想以指定的时间格式返回，需要做如何处理？
    解决方案：
    方式一： 在application.properties 属性文件中增加两个属性配置：
            spring.jackson.time-zone=GMT+8
            spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
    方式二：注解JsonFormat
         @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
         private Date time;

2、如何控制接口输出字段都为时间戳：yml或properties文件，参数类型控制
    spring.jackson.serialization.WRITE_DATES_AS_TIMESTAMPS=true

总结一下：
    1》Convert解决的问题是类型间的转化，case-String转化为date
    2》BeanSerializerModifier解决的是序列化数据，Jackson序列化方式，case-

