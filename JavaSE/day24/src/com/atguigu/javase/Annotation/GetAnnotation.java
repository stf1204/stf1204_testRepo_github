package com.atguigu.javase.Annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author stf
 * 注解解析步骤：
 * 1、反射，获取一个类class文件对象
 * 2、反射，获取方法
 * 3、方法判断是否有注解
 * 4、解析注解
 *
 */
public class GetAnnotation {

    public static void main(String[] args) throws Exception{

        // 反射获取类对象
        Class<?> ab = Class.forName("com.atguigu.javase.Annotation.BookStore");

        //反射获取方法
        Method read = ab.getMethod("read");

        //判断是否有注解
        boolean b = read.isAnnotationPresent(bookInterface.class);
        System.out.println("b = " + b);

        //获取注解值
        bookInterface annotation = read.getAnnotation(bookInterface.class);
        String[] authors = annotation.authors();
        System.out.println("authors = " + Arrays.toString(authors));
        System.out.println(annotation.name());
        System.out.println(annotation.price());
        System.out.println(annotation.count());


    }
}
