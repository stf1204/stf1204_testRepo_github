package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 带回调的producer
 * callback()
 *
 * @author stf
 */
public class CustomProducerCallBack {
    public static void main(String[] args) {

        //1、  创建kafka生产者的配置对象
        Properties proper = new Properties();
        // 给kafka配置对象添加配置信息：bootstrap.servers
        proper.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "master:9092");

        //2、  k，v 的序列化（必须）：key.serializer，value.serializer
        proper.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        proper.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        //3、 创建生产者 对象
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(proper);

        for (int i = 0; i < 5; i++) {

            //4、 调用send方法,发送消息
                // 默认使用粘性分区，尽量往同一个分区写数据
            producer.send(new ProducerRecord<String, String>("MyTopic","badou---------" + i), new Callback() {
                // 按照 key的hash值对分区数取余进行选择要写的分区号
            // producer.send(new ProducerRecord<String, String>("MyTopic","badou---------" + i,"badou---------" + i){
                // 固定分区数，往2分区写数据
            // producer.send(new ProducerRecord<String, String>("MyTopic",2,"","badou---------" + i) {

                @Override
                public void onCompletion(RecordMetadata meTaData, Exception e) {
                    if (e == null) {
                        System.out.println("主题："+meTaData.topic()+"-------------分区："+meTaData.partition());
                    }
                    else {
                        e.printStackTrace();
                    }
                }
            }
            );
        }

        // 5 关资源
        producer.close();
    }
}
