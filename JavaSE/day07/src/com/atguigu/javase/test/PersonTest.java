package com.atguigu.javase.test;


import com.atguigu.javase.javabean.Person;
import com.atguigu.javase.javabean.Phone;

public class PersonTest {
    public static void main(String[] args) {
        Phone phone = new Phone("安卓", 11.2, 128);
        Person p = new Person("小明", 22, 30);
        p.play(phone);
    }
}
