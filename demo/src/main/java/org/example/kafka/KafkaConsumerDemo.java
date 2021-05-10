package org.example.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerInterceptor;

import java.util.Collections;
import java.util.Properties;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/21 17:22
 */
public class KafkaConsumerDemo {

    private final Consumer<String, String> consumer;

    private KafkaConsumerDemo() {
        //1、构造Properties对象
        Properties props = new Properties();
        //注意消费端需要配置成zk的地址，而生产端配置的是kafka的ip和端口。
        //服务器ip:端口号，集群用逗号分隔
        props.put("bootstrap.servers", "XXXX:2181");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        // 自动commit的间隔
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //2、构造KafkaConsumer对象
        consumer = new KafkaConsumer<>(props);
        //3、订阅topic列表
        consumer.subscribe(Collections.singletonList("new-msg-test"));
    }

    /**
     * 进行消息消费操作
     */
    void consume() {
        while (true) {
            /**拉取订阅的消息
             * 参数timeout(ms): buffer 中的数据未就绪情况下，等待的最长时间，如果设置为0，
             * 立即返回 buffer 中已经就绪的数据。
             * 如果给定了参数，那么等待时间超过了指定超时时间就返回。
             */
            ConsumerRecords<String, String> records = consumer.poll(100);
            if (records.count() > 0) {
                //拉取的数据量，count为数据个数，
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    System.out.println("从kafka接收到的消息key是：" + key);
                    //每条消息的value
                    String message = record.value();
                    System.out.println("从kafka接收到的消息value是：" + message);
                }
            }
        }

    }

    public static void main(String[] args) {
        new KafkaConsumerDemo().consume();
    }
}
