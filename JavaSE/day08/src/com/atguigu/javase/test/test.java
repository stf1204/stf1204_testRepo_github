package com.atguigu.javase.test;

import com.atguigu.javase.Person;
import com.atguigu.javase.Phone;

public class test {
    public static void main(String[] args) {
        Phone phone = new Phone("安卓", 11.2, 128);
        Person p = new Person("小明", 22, 30,phone);
        p.game();
        p.shop();
    }
}
