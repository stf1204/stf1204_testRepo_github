package com.atguigu.javase.polymorphism;

public class Peking extends Chinese {

    public void PlayBird(){
        System.out.println("遛鸟...");
    }

    @Override
    public void  sayhello(){
        System.out.println("chi le wa");
    }

}
