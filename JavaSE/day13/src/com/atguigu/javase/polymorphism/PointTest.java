package com.atguigu.javase.polymorphism;

public class PointTest {
    public static void main(String[] args) {
        Point p1 = new Point(12, 21);
        Point p2 = new Point(12, 21);

        System.out.println(p1==p2);
        System.out.println("---------------test-------------");
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals("abc"));
        System.out.println("---------------test-------------");
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println("---------------test-------------");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p1);
        System.out.println(p2); //会自动调用 toString方法
        String s="abc";
        s+=p1; //拼接字符串也会自动调用toString
        System.out.println(s);


    }
}
