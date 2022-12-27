package com.atguigu.javase.thread;

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
            synchronized (r) {
                if (!r.flag) {
                    try {
                        // wait方法通过对象锁用户来调用
                        r.wait();
                    } catch (Exception e) {
                    }
                }
                System.out.println("消费第" + r.count + "中");

                // 消费完 该生产了
                r.flag=false;

                // notify方法通过对象锁用户来调用
                r.notify();
            }
        }
    }
}
