package com.atguigu.javase.test1;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author stf
 * 读取配置文件，获得需要榨汁的水果类
 * 反射实例化对象
 * 创建榨汁机对象，调用run方法
 * 传入水果参数进行榨汁
 */
public class FruitTest {
    public static void main(String[] args) throws Exception {
        // 获取配置文件信息流
        Class<FruitTest> ftc = FruitTest.class;
        InputStream is = ftc.getClassLoader().getResourceAsStream("fruit.properties");

        //读取配置文件信息
        Properties pro = new Properties();
        pro.load(is);
        String fruitName = pro.getProperty("fruitName");

        //反射创建需要的对象
        Class cla = Class.forName(fruitName);
        Object o = cla.newInstance();

        //实例化榨汁机，扎水果
        Juicer juicer = new Juicer();
        juicer.run((Fruit)o);

    }

}
