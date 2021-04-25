![img.png](img.png)

#### 吞吐量大、低延时
![img.png](../img.png)
>kafka本身是基于磁盘读写的，得益于是顺序读写，如上图，每一个partition都是一个文件，
> 数据追加在partition的尾部，写消息只允许追加写入，不允许修改已经写入的数据。

一条消息存储于某一个具体的partition。同一个topic消息可存储于不通的partition(负载均衡)

##### kafka 元数据在 zk 中的分布
![img_1.png](../img_1.png)



![img_2.png](img_2.png)











