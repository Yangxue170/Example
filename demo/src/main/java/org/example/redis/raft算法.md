### raft算法
[http://thesecretlivesofdata.com/raft/#election][动态raft图示]

[https://www.cnblogs.com/developing/articles/11129289.html][静态文档解释]

主要的状态：Follower-----Candidate----Leader



#### 脑裂问题-网络分区
原因：由于网络环境问题，出现网络分区，使得一部分数据重新选举出leader，待网络恢复后，出现多个leader的场景，称为脑裂。
解决：
raft算法中，脑裂问题也可以很好的解决，每个节点都有一个term属性，表示选举次数，每次选举term+1，term的值同时表示数据的最新值。
当出现脑裂场景时，新选举的leader的term值将会+1，待到网络恢复后，term值大的数据最新，此时以term值大的作为leader，其余则退化为follower。

此处有个点需要注意⚠️：脑裂之后，
1、原leader在多数节点的时候，少数节点达不到节点总数的1/2，投票数不足1/2竞选不出leader，一直在自旋选举。网络恢复不影响。
2、原leader在少数节点的时候，多数节点达到了总数的1/2则会选举出新的leader，其term值+1。网络恢复后，根据term值大的为leader，小的降级为follower。
   老leader同步新leader数据

注意⚠️：
但是会出现stale read场景：
`stale read 是发生脑裂时，老leader不知道新的leader产生，此时客户端请求老leader时，读取的是老数据，这种场景称为 stale read。`
解决方案：
>所有的读写请求都必须通过 region leader 完成

引入一个新的概念, region leader。region leader 是一个逻辑上的概念, 任意时刻对于某一个 region 来说, 一定只拥有一个 region leader。
每个region leader在任期之内尝试每隔t时间间隔, 在raft group内部更新一下region leader的lease. 

四种场景：
`
region leader 落在多数派，老 raft leader 在多数派这边。(不会出现 stale read)
region leader 落在多数派，老 raft leader 在少数派这边。
region leader 落在少数派，老 raft leader 在多数派这边。
region leader 落在少数派，老 raft leader 在少数派这边。
`
这种方法牺牲了一定的可用性（在脑裂时部分客户端的可用性）换取了一致性的保证。


