package com.atguigu.jedis;

/**
 * @ClassName: Jedis
 * @PACKAGE: com.atguigu.spark.jedis
 * @Author: stf
 * @Date: 2022/12/22 - 13:32
 * @Description:
 * @Version: v1.0
 */
public class Jedis1 {

    public static void main(String[] args) {

        // 创建客户端连接对象
        redis.clients.jedis.Jedis je = new redis.clients.jedis.Jedis("hadoop102", 6379);
        // 使用对象
        String ping = je.ping();
        System.out.println(ping);
        // 关闭对象
        je.close();
    }
}
