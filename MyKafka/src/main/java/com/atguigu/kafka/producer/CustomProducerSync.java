package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * 同步producer——添加  .get()
 * @author stf
 */
public class CustomProducerSync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1、  创建kafka生产者的配置对象
        Properties properties = new Properties();
            // 给kafka配置对象添加配置信息：bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"master:9092");

        //2、  k，v 的序列化（必须）：key.serializer，value.serializer
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        //3、 创建生产者 对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);


        for (int i = 0; i < 5; i++) {
            //4、 调用send方法,发送消息
            producer.send(new ProducerRecord<String, String>("MyTopic","badou---------"+i)).get();
        }

        // 5 关资源
        producer.close();
    }
}
