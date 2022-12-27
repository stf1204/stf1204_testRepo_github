package com.atguigu.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author stf
 */
public class CustomConsumerGroup1 {
    public static void main(String[] args) {

        // 1
        Properties properties = new Properties();

        // 连接kafka集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092");

        // kv 反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 2 指定Group Id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"MyTopic4");


        // 6 设置分区分配策略----默认rangeAssignor
        // properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RangeAssignor.class.getName());
        // properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName());
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, StickyAssignor.class.getName());


        // 7 指定 offset 是否自动提交 (默认true)
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        // 指定offset 多久提交一次
        properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG,5000);


        // 8 指定offset 消费的起始位置，默认为latest，还有earliest，none
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");




        // 3 创建kafka consumer对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 4 consumer订阅 topic
        List<String> topic = new ArrayList<>();
        topic.add("MyTopic");
        kafkaConsumer.subscribe(topic);



        // 多个consumer消费同一个 partition  ---| 没啥用

        // List<TopicPartition> topic = new ArrayList<>();
        // TopicPartition myTopic = new TopicPartition("MyTopic", 0);
        // topic.add(myTopic);
        // kafkaConsumer.assign(topic);



        // 指定topic的具体分区的具体offset，来进行消费  ---| 可能会用到

        // List<TopicPartition> topic = new ArrayList<>();
        // // 指定消费某一topic的某一分区
        // TopicPartition myTopic = new TopicPartition("MyTopic", 0);
        // TopicPartition myTopic1 = new TopicPartition("MyTopic1", 1);
        // topic.add(myTopic);
        // topic.add(myTopic1);
        // kafkaConsumer.assign(topic);
        // // 指定消费 某一topic 的某一分区 的具体offset
        // kafkaConsumer.seek(myTopic,15);
        // kafkaConsumer.seek(myTopic1,25);


        // 5
        while (true){
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, String> consumerRecord : poll) {
                System.out.println(consumerRecord);
            }

            // 与上面的offset的配置配合，指定异步 手动提交offset
            kafkaConsumer.commitAsync();

            // 与上面的offset的配置配合，指定同步 手动提交offset
            kafkaConsumer.commitSync();
        }
    }


}
