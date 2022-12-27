package com.atguigu.javase;

/**
 * @author stf
 * 继承实现线程
 */
public class extend extends java.lang.Thread {
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("run......"+super.getName());
        }
    }
}

