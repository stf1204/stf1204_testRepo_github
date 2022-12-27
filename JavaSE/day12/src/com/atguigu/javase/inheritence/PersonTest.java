package com.atguigu.javase.inheritence;

public class PersonTest {
    public static void main(String[] args) {
        Chinese ch = new Chinese();
        ch.setName("张三");
        ch.setAge(18);
        ch.setGender("男");
        ch.setShuxiang("牛");
        ch.sayhello();
        System.out.println(ch.say());
        ch.spring();
    }
}
