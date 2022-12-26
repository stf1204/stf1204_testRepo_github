package com.atguigu.javase.test1;

public class Apple implements Fruit {
    @Override
    public void squeeze() {
        System.out.println("苹果只能榨苹果汁");
    }
}
