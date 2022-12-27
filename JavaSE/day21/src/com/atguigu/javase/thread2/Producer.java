package com.atguigu.javase.thread2;

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
            r.set();
        }
    }
}
