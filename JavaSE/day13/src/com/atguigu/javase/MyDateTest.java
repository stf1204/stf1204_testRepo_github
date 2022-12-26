package com.atguigu.javase;

public class MyDateTest {
    public static void main(String[] args) {
        MyDate m1 = new MyDate(2022, 9, 30);
        MyDate m2 = new MyDate(2022, 9, 30);

        System.out.println(m1.equals(m2));
        System.out.println("*****************************************");
        System.out.println(m1.equals("abc"));
        System.out.println("*****************************************");
        System.out.println(m1.hashCode());
        System.out.println(m2.hashCode());
        System.out.println("*****************************************");
        System.out.println(m1);
        System.out.println(m2);
        System.out.println(m1.toString());
        System.out.println(m2.toString());
        String m3 ="aaaaaaaaaaaa";
        m3+=m2;
        System.out.println(m3);

    }
}
