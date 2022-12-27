package com.atguigu.javase.polymorhpysm;

public class PersonTest {
    public static void main(String[] args) {

        Person p = new Chinese("小明", 22, "男", "狗");  // 父态引用
        Chinese pp = new Chinese("小明", 22, "男", "狗"); // 本态引用
        p.say();
        //p.spring();  多态副作用
        pp.spring();

    }
}
