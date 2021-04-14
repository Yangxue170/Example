
1、自定义过滤器，必须实现javax.servlet.Filter，
然后添加注解 @WebFilter（javax.servlet.annotation.WebFilter），
urlPatterns 过滤器要过滤的URL规则配置，filterName(不配置则是类名，首字母小写)过滤器的名称。

    注意：@Order(int) 注解，配合 @WebFilter 注解使用，用于多个过滤器时定义执行顺序，值越小越先执行。
    但是：项目中实际使用时，发现此注解不生效，源码发现@WebFilter 修饰的过滤器在加载时，
    没有使用 @Order 注解，而是使用的类名来实现自定义Filter顺序。（首字母顺序）

2、在启动类上加一个注解 @ServletComponentScan，无此注解则不会进入到自定义filter中

3、