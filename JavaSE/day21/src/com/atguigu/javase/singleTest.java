package com.atguigu.javase;

class threads implements Runnable{
    /**
     * 单例模式 多线程创建对象
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(DCLSingle.getInstance());
        }
    }
}

/**
 * @author stf
 */
public class singleTest {

    public static void main(String[] args) {
        Runnable t2=new threads();

        Thread t1 = new Thread(t2);
        Thread t22 = new Thread(t2);

        t1.start();
        t22.start();
    }
}
