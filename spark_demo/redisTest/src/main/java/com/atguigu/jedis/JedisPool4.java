package com.atguigu.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

/**
 * @ClassName: Jedis
 * @PACKAGE: com.atguigu.spark.jedis
 * @Author: stf
 * @Date: 2022/12/22 - 13:32
 * @Description:
 * @Version: v1.0
 */
public class JedisPool4 {

    public static void main(String[] args) {

        //主要配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大可用连接数
        jedisPoolConfig.setMaxTotal(10);
        //最大闲置连接数
        jedisPoolConfig.setMaxIdle(5);
        //最小闲置连接数
        jedisPoolConfig.setMinIdle(5);
        //连接耗尽是否等待
        jedisPoolConfig.setBlockWhenExhausted(true);
        //等待时间
        jedisPoolConfig.setMaxWaitMillis(2000);
        //取连接的时候进行一下
        jedisPoolConfig.setTestOnBorrow(true);

        // 创建客户端连接对象
        JedisPool jes = new JedisPool(jedisPoolConfig,"hadoop102", 6379);
        Jedis je = jes.getResource();
//        redis.clients.jedis.Jedis je = new redis.clients.jedis.Jedis("hadoop102", 6379)
        // 使用对象
        je.sadd("zhangsan", "name", "age", "gender", "grade", "score");

        Set<String> stringSet = je.smembers("zhangsan");
        System.out.println("sadd = " + stringSet);

        // 关闭对象
        je.close();
    }
}
