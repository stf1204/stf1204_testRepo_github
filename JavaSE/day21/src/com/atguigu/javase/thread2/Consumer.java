package com.atguigu.javase.thread2;

/**
 *消费者线程  负责打印消费数据
 * @author stf
 */
public class Consumer implements Runnable {

    /**
     *创建资源对象
     */
    private Resource r;

    public Consumer(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
           r.get();
        }
    }
}
