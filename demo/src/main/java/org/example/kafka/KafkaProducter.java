package org.example.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/4/21 17:19
 */
public class KafkaProducter {

    private final KafkaProducer<String, String> producer;
    public final static String TOPIC = "new-msg-test";

    private KafkaProducter(){
        //1、构造Properties对象，获取配置参数
        Properties properties = new Properties();
        //此处配置的是kafka的端口
        properties.put("bootstrap.servers", "XXXX:9092");
        //序列化与反序列化方式
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //确认方式
        /**
         * 0：这意味着生产者producer不等待来自broker同步完成的确认继续发送下一条（批）消息。
         * 1：这意味着producer在leader已成功收到的数据并得到确认后发送下一条message。
         * -1：这意味着producer在follower副本确认接收到数据后才算一次发送完成。
         */
        properties.put("request.required.acks","-1");
        //2、构造KafkaProducer对象 client
        producer = new KafkaProducer<>(properties);
    }

    /**
     * 发送消息的具体方法
     */
    void produce() {
        //发送100条消息
        int messageNo = 100;
        int count = 200;
        while (messageNo < count) {
            //消息的key
            String key = String.valueOf(messageNo);
            //消息的value
            String data = "hello kafka message " + key;
            //时间戳
            long startTime = System.currentTimeMillis();
            //3、构造ProducerRecord对象
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, key, data);
            //发送消息到Kafka broker,使用异步发送的回调函数支持，有问题之类的处理方式，会执行onCompletion()方法。
            //4、发送消息
            producer.send(record, new DataCallback(startTime, data));
            System.out.println(data);
            messageNo++;
        }
        //5、关闭producer
        producer.close();
    }
    public static void main( String[] args ) {
        //生产消息
        new KafkaProducter().produce();
    }
}
