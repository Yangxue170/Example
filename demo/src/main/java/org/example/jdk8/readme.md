
###Arraylist线程不安全
case: org.example.jdk8.ListDemo
> - vector,所有方法都具有 synchronized 关键修饰。
> - Collections.synchronizedList(new ArrayList<>())所有方法都是带同步对象锁synchronized
> - ThreadLocal 每new一个新的线程，变量也会new一次，一定程度上会造成性能[内存]损耗

上面的方法性能较差，读多写少的情况，使用？
> - java.util.concurrent.CopyOnWriteArrayList 
> - java.util.concurrent.CopyOnWriteArraySet



####HashSet线程不安全
用CopyOnWriteArraySet（底层还是用的CopyOnWriteArrayList）

####HashMap线程不安全
使用Collections.synchronizedMap(new HashMap<>())
使用ConcurrentHashMap

Map<String, String> map = new ConcurrentHashMap<>();