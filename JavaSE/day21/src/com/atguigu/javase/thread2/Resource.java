package com.atguigu.javase.thread2;

/**
 * @author stf
 */
public class Resource {

    private int count=0;

    private boolean flag = false;

    /**
     * 生产者，同步函数
     */
    public synchronized void set(){
        if (flag){
            try {
                // wait方法通过对象锁用户来调用
                this.wait();
            } catch (Exception e) { }
        }
        count++;
        System.out.println("生产第...."+count+"中");
        // 消费完 该生产了
        flag=true;
        // notify方法通过对象锁用户来调用
        this.notify();

    }
    /**
     * 消费者,同步函数
     */
    public synchronized void get(){
        if (!flag){
            try {
                // wait方法通过对象锁用户来调用
                this.wait();
            } catch (Exception e) { }
        }
        System.out.println("消费第"+count+"个");
        // 生产完该消费了
        flag=false;
        // notify方法通过对象锁用户来调用
        this.notify();
    }
}
