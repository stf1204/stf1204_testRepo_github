package com.atguigu.javase.Example;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 执行哪个类的哪个方法，写入配置信息，通过流读取信息
 * @author stf
 *
 * 1、创建配置文件，键值对，存储在src目录下面
 * 2、字节输入流读配置文件
 * 3、将读取的键值对存储在properties集合中
 * 4、集合通过键值对获取值
     * className=com.atguigu.javase.Example.Student
     * methodName=play
 * 5、反射创建对象
 * 6、反射获取方法，执行方法
 *
 *
 */

public class Test {
    public static void main(String[] args) throws Exception{
        test2();

    }

    /**
     *
     * 字节输入流读配置文件
     * 将读取的键值对存储在properties集合中
     * 集合通过键值对获取值
     *  className=com.atguigu.javase.Example.Student
     *  methodName=play
     * 反射创建对象
     * 反射获取方法，执行方法
     *  *
     */
    public static void test() throws Exception{
        // 先获取本类对象
        Class tc = Test.class;
        // 获取本类加载器，调用本类加载器方法getResourceAsStream("文件名")
        // 返回字节输入流，自动从src下面读取文件
        InputStream ins = tc.getClassLoader().getResourceAsStream("config.properties");

        //读取到的键值对存储到properties集合中
        Properties pro = new Properties();
        pro.load(ins);
        ins.close();
        // System.out.println("pro = " + pro)

        //读取信息
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");
        // System.out.println("className = " + className)
        // System.out.println("methodName = " + methodName)

        // 获取到类，可以反射创建对象
        Class ac = Class.forName(className);

        // 获取到类，可以反射方法
        Object o = ac.newInstance();
        Method method = ac.getMethod(methodName);
        method.invoke(o);

    }

    public static void test2() throws Exception{
        //流部分
        Class cls = Test.class;
        InputStream ins = cls.getClassLoader().getResourceAsStream("config.properties");

        // 集合部分
        Properties pr = new Properties();
        pr.load(ins);
        // System.out.println("pr = " + pr);
        String methodName = pr.getProperty("methodName");
        String className = pr.getProperty("className");
        // System.out.println("methodName = " + methodName); play
        // System.out.println("className = " + className);  com.atguigu.javase.Example.Student

        // 反射部分
        Class<?> ac = Class.forName(className);
        Object oo = ac.newInstance();
        Method meth = ac.getMethod(methodName);
        meth.invoke(oo);
    }
}
