# Spring
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

