package com.atguigu.javase.test;

public class PointTest {

    public static void main(String[] args) {
        com.atguigu.javase.javabean.Point p = new com.atguigu.javase.javabean.Point();
        System.out.println(p.getX0());
        System.out.println(p.getX1());
        System.out.println("----------------");
        System.out.println(p.getDistance());
        System.out.println("--------------------");
        p.setX0(88);
        p.setX1(90);
        System.out.println(p.getDistance());
        System.out.println("-------------------");
        com.atguigu.javase.javabean.Point p2 = new com.atguigu.javase.javabean.Point(10,20);
        System.out.println(p2.getDistance());
        p2.say();
    }
}
