package com.atguigu.kafka.producer.auto_partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author stf
 */
public class AutoPartitioner implements Partitioner {
    /**
     * 实现分区功能的函数（具体的业务逻辑）
     * @param topic topic名称
     * @param key 消息的key
     * @param keyBytes 消息key 序列化以后的字节数组
     * @param value 消息的value
     * @param valueBytes 消息value 序列化以后的字节数组
     * @param cluster 集群元数据
     * @return
     */
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String values = value.toString();

        if (values.contains("badou")){
            return 1;
        }
        else {
            return 0;
        }
    }


    /**
     * 关闭资源的函数
     */
    public void close() {

    }



    /**
     * 配置信息的函数
     */
    public void configure(Map<String, ?> map) {

    }
}
