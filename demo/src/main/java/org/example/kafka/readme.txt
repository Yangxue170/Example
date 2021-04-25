
简单来说，kafka就是一个消息中间件

1、kafka与zk关系
Kafka 使用 ZooKeeper 来保存与分区和代理相关的元数据，并选举出一个代理作为集群控制器。目前kafka团队想要消除zk的依赖。
Kafka需要使用Zookeeper来管理元数据，比如记录topic、partitions（分区）以及replica（副本）的分配信息。

kafka设计开始，主要是解决四个问题：
- 吞吐量/延时
- 消息持久化
- 负载均衡/鼓掌转移
- 伸缩性

Kafka是将数据持久化到本地磁盘中，

2、zk上存储的kafka数据有哪些


3、kafka清除topic数据

4、topic与partition关系，一条消息只可能属于一个partition

