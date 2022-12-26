package com.atguigu.javase.Reflect;

import java.lang.reflect.Constructor;

/**
 * 通过简单方法以及有参，无参构造函数，使用getInstance()方法实例对象
 * @author stf
 */
public class GetObject {

    public static void main(String[] args) throws Exception{
        noConstructorGetObject();

    }

    /**
     * 1、无参构造器创建对象
     */
    public static void noArgsGetObject() throws Exception{

        //1、获取任意一个类的类对象
        Class<?> acla = Class.forName("com.atguigu.javase.Reflect.Person");

        //2、实例化构造器对象
        Constructor constructor = acla.getConstructor();
        // System.out.println("constructor = " + constructor)

        //3、通过构造器的newInstance()，获取对象
        Object obs = constructor.newInstance();

        //4、打印对象信息
        System.out.println("obs = " + obs);
    }


    /**
     * 2、全参构造器创建对象
     */
    public static void allArgsGetObject() throws Exception {

        // 获取类对象
        Class cla = Class.forName("com.atguigu.javase.Reflect.Person");

        // 获取类的有参构造方法
        Constructor con = cla.getConstructor(String.class,int.class);

        //实例化对象
        Object obs =  con.newInstance("张三",22);
        System.out.println("obs = " + obs);
    }


    /**
     * 3、简单对象方法
     * newInstance()
     * 该方法有一个前提，实例化类必须有公共的无参构造器
     */
    public static void noConstructorGetObject() throws Exception{

        //1、获取class文件对象
        Class cls = Class.forName("com.atguigu.javase.Reflect.Person");

        //2、class文件对象直接调用newInstance()实例化对象
        Object o = cls.newInstance();

        System.out.println("o = " + o);
    }
}
