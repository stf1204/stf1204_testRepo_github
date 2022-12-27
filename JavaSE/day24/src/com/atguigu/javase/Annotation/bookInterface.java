package com.atguigu.javase.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 关键字为@interface
 * 注解成员：本质上就是抽象方法
 * 默认值:就是抽象方法的返回值
 *
 *
 * 注解中的数据类型：
 *      八种基本数据类型和String类型，枚举类型，Class类型，其他注解类型，
 *      以上所有类型的一维数组
 *
 *
 *1、 添加指挥官：Retention注解：限定生存的位置
 *    Retention属性值是value，数据类型是RetentionPolicy(枚举型)
 *    有三个常量：  SOURCE源码中，CLASS类中，RUNTIME运行时
 *
 *2、 添加指挥官：Target注解：限定可以注解的位置
 *    Target属性值是value，是一个ElementType[]，类型是枚举
 *    有四个常用常量： TYPE类上，METHOD方法上，FIELD字段上，CONSTRUCTOR构造器上
 * @author stf
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface bookInterface {
    //定义注解的方法也叫属性

    // 书名属性
    String name();

    // 单价
    double price();

    // 作者
    String[] authors();

    // 库存
    int count() default 100;


}
