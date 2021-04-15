目前项目中大多使用mybatis进行数据库操作，但是会遇到分页不生效的问题：

1、问题原型是，可以查出所有的数据，但是查出的total是0，打断点发现参数确实传入进去
    参考：https://www.cnblogs.com/yadongliang/p/13439800.html
    方案一：添加拦截器org.example.mybatis.MybatisPlusConfig.mybatisPlusInterceptor
    方案二：数据源配置中显式添加分页插件 org.example.mybatis.PageSqlFactoryCon.sqlSessionFactory