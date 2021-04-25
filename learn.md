小白学习

## Spring
Spring及其亲属learn

## 事件
> 事件简单的说就是触发某种操作（村里某家的鸡丢了，村支书大喇叭喊谁见过，其余村民纷纷讨论是否见过）。
>【发送者    -----事件------>   接收者】

java中的事件机制的参与者有3种角色：

1.event object：就是事件产生时具体的“事件”，
用于listener的相应的方法之中，作为参数，一般存在于listener的方法之中.

2.event source：具体的接受事件的实体，
比如说，你点击一个button，那么button就是event source，
这样你必须使button对某些事件进行响应，你就需要注册特定的listener，
比如说MouseEvent之中的 MouseClicked方法，这时它就必须有了add方法

3.event listener：具体的进行监听的事件类，
当有其对应的event object产生的时候，它就调用相应的方法，进行处理。

对于链接：
https://www.cnblogs.com/taobean/p/12364278.html

### 统计QPS:
日常工作中有时会需要统计QPS，最简单粗暴的方法就是根据日志来统计。

下面简单记录下统计过程

cat access.log | grep "/puzu/focus/resblcoks" | cut -d' ' -f2 | cut -d':' -f3|cut -d'.' -f1 | uniq -c |awk 'NR==1{max=$1;next}{max=max>$1?max:$1}END{print max/2}'
分解：
1.输出内容
2.利用grep过滤出想要统计的接口
3.cut用于切割上面过滤的一行。 cut -d'[' -f1 表示根据"["来切割一行，-f1表示取切割后的第一列。
4.uniq -c用于去重
5.awk 用于计算最值

上述操作合起来就可以统计出接口QPS，然而这个只是单台机器，最终结果需要乘以机器数。
第一步cat一般用于统计以往日志，如果想实时统计，可以使用tail -f access.log命令。
常用命令：
```
# 实时统计
tail -f access.log | grep "{placeholder}" | cut -d' ' -f2 | cut -d':' -f3|cut -d'.' -f1 | uniq -c
# 统计并直接输出最大值
cat access.log | grep "/puzu/focus/resblcoks" | cut -d' ' -f2 | cut -d':' -f3|cut -d'.' -f1 | uniq -c |awk 'NR==1{max=$1;next}{max=max>$1?max:$1}END{print max}'
```
