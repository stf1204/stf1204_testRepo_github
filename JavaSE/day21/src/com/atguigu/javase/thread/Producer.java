package com.atguigu.javase.thread;

/**
 *生产者线程    负责生产数据
 * @author stf
 */
public class Producer implements Runnable{

    /**
     *创建资源对象
     */
    private Resource r;

    /**
     * 消费同一个数据
     */
    public Producer(Resource r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
            // 加对象锁
            synchronized (r){
                if (r.flag){
                    try {
                        // wait方法通过对象锁用户来调用
                        r.wait();
                    } catch (Exception e) { }
                }
                r.count++;
                System.out.println("生产第...."+r.count+"个");

                // 生产完该消费了
                r.flag=true;

                // notify方法通过对象锁用户来调用
                r.notify();
            }
        }
    }
}
