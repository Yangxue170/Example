
servlet之旅

1、ttpServletRequest只允许读取一次(流数据的原因).数据进入逻辑处理前一帮会经过filter的处理，
若filter读取了getInputStream()之后，后续对于getInputStream()读取数据都为空。
解决方式：
使用代理模式，返回伪造”的`HttpServletRequest`，对`getInputStream()`和`getReader()`返回一个新的流：
例如：org.example.servlet.RepeatReadServletRequest

2、Filter(过滤器) 只是作用于http请求

