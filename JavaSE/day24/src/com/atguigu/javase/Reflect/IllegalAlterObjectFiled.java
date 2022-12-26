package com.atguigu.javase.Reflect;

import java.lang.reflect.Field;

/**
 * @author stf
 * 暴力反射，获取私有字段，并修改值
 * Field Constructor Method 三个类继承一个共同的父亲
 * ObjectAccessible类  方法setAccessible(true)访问私有成员
 */
public class IllegalAlterObjectFiled {

    public static void main(String[] args) throws Exception {
        illegalAlterFiled();
    }

    /**
     * 修改私有变量的值
     *   getField()、getFields() 只能获取被反射的类中所有公共字段，Field字段对象(成员变量对象)
     *   getDeclaredField()、getDeclaredFields()获取声明的对象，可以用来获取私有字段
     * @throws Exception
     */
    public static void illegalAlterFiled() throws Exception {

        //获取类对象
        Class cla = Class.forName("com.atguigu.javase.Reflect.Person");

        // Class类的方法，获取字段
        // getField()、getFields() 获取被反射的类中所有公共字段，Field字段对象(成员变量对象)
        // getDeclaredField()、getDeclaredFields()获取声明的对象，可以用来获取私有字段
        Field[] fields = cla.getDeclaredFields();
        for (Field field:fields){
            System.out.println("field = " + field);
        }

        //获取声明的变量
        Field name = cla.getDeclaredField("name");
        System.out.println("name = " + name);

        //实例化对象
        Object obj = cla.newInstance();

        // 取消java检查访问权限
        name.setAccessible(true);
        name.set(obj,"李四");
        System.out.println("obj = " + obj);



    }

}
