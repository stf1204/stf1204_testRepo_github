package com.atguigu.kafka.producer.auto_partitioner;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author stf
 */
public class Consumer_Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //1 获取连接
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092");

        // 2 kv序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        //---设置自定义分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,AutoPartitioner.class.getName());

        // 3 kafka_producer对象
        KafkaProducer<String, String> pro = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 5; i++) {

            // 4 send数据
            pro.send(new ProducerRecord<String, String>("MyTopic", "baddou----" + i), new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e==null){
                        System.out.println("topic:"+recordMetadata.topic()+"--------partition:"+recordMetadata.partition());
                    }
                }
            });

        }

        // 5 close资源
        pro.close();

    }
}
