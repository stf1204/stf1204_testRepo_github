package com.atguigu.javase;

/**
 * 通过实现接口来实现线程
 * @author stf
 */
public class interFace implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("run2..... " + i);
        }

    }

}
