package com.atguigu.javase.thread;

/**
 * @author stf
 */
public class Rtest {
    public static void main(String[] args) {
        // 创建资源对象
        Resource r = new Resource();
        // 生产者对象
        Producer producer = new Producer(r);
        //消费者对象
        Consumer consumer = new Consumer(r);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        //开启生产者线程
        t1.start();
        //开启消费者线程
        t2.start();
    }
}
