# Dubbo
####Dubbo的总体架构图
> 是一种RPC框架，RPC-Remote Procedure Call 即远程过程调用，远程过程调用其实对标的是本地过程调用。 

#### 作用
它实现了面向接口的代理 RPC 调用，并且可以配合 ZooKeeper 等组件实现服务注册和发现功能，并且拥有负载均衡、容错机制等。
![img_6.png](img_6.png)

#### Dubbo架构
![img_7.png](img_7.png)
>Provider	暴露服务的服务提供方
Consumer	调用远程服务的服务消费方
Registry	服务注册与发现的注册中心
Monitor	统计服务的调用次数和调用时间的监控中心
Container	服务运行容器


####每个节点作用
![img_1.png](img_1.png)

####消费过程
![img_2.png](img_2.png)

####服务调度、流量治理
![img.png](img-3.png)

####RPC框架的case解释
![img_3.png](img_3.png)
> Dubbo只是rpc框架的一种

![img_4.png](img_4.png)
#### 注册中心
> 类似于婚介中心，男、女各自需求进行匹配

![img_5.png](img_5.png)


















