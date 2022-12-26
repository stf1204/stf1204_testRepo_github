package com.atguigu.jedis;

import java.util.Set;

/**
 * @ClassName: Jedis
 * @PACKAGE: com.atguigu.spark.jedis
 * @Author: stf
 * @Date: 2022/12/22 - 13:32
 * @Description:
 * @Version: v1.0
 */
public class Jedis3 {

    public static void main(String[] args) {

        // 创建客户端连接对象
        redis.clients.jedis.Jedis je = new redis.clients.jedis.Jedis("hadoop102", 6379);
        // 使用对象
        je.sadd("zhangsan", "name", "age", "gender", "grade", "score");

        Set<String> stringSet = je.smembers("zhangsan");
        System.out.println("sadd = " + stringSet);

        // 关闭对象
        je.close();
    }
}
