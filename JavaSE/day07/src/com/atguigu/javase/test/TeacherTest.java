package com.atguigu.javase.test;

public class TeacherTest {
    public static void main(String[] args) {
        com.atguigu.javase.javabean.Teacher t1 = new com.atguigu.javase.javabean.Teacher();
        // t1.name="宁宁";
        // t1.age=22;
        // t1.gender="女";
        t1.setName("宁宁");
        t1.setAge(22);
        t1.setGender("女");

        // System.out.println(t1.name);
        // System.out.println(t1.age);
        // System.out.println(t1.gender);
        System.out.println(t1.getName());
        System.out.println(t1.getGender());
        System.out.println(t1.getAge());
        System.out.println(t1.say());
        t1.lesson();
        t1.eat("柠檬");

        com.atguigu.javase.javabean.Teacher t2 = new com.atguigu.javase.javabean.Teacher();
        System.out.println(t2.say());

        // t2.name="刚子";
        // t2.age=23;
        // t2.gender="男";
        t2.setName("刚子");
        t2.setAge(21);
        t2.setGender("男");


        System.out.println(t2.say());

        com.atguigu.javase.javabean.Teacher t3 = new com.atguigu.javase.javabean.Teacher();
        System.out.println(t3.say());

        System.out.println("----------------------------");
        com.atguigu.javase.javabean.Teacher t4 = new com.atguigu.javase.javabean.Teacher();
        System.out.println(t4.say());
        System.out.println("----------------------------");
        com.atguigu.javase.javabean.Teacher t5 = new com.atguigu.javase.javabean.Teacher("小黑",18,"男");
        System.out.println(t5.say());
        System.out.println("----------------------------");
        com.atguigu.javase.javabean.Teacher t6 = new com.atguigu.javase.javabean.Teacher("小白",18,"女");
        System.out.println(t6.say());
        System.out.println("--------------------------------");
    }
}
