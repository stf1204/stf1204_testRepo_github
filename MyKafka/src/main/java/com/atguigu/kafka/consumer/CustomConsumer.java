package com.atguigu.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author stf
 */
public class CustomConsumer {
    public static void main(String[] args) {

        // 1、 properties
        Properties properties = new Properties();

        // 2、put 参数
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


        // 3、put Group Id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "MyTopic");

        // 4、new 消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 5、消费者订阅topic
        List<String> topics = new ArrayList<>();
        topics.add("MyTopic");
        consumer.subscribe(topics);


        // 6、消费数据
        while (true) {
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, String> consumerRecord : poll) {
                System.out.println(consumerRecord);
            }
        }
    }
}
