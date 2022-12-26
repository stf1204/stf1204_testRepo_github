package com.atguigu.javase;

/**
 * @author stf
 */
public class TestThread {
    /**
     * 匿名内部类
     */

    public static void main(String[] args) {

        // 匿名线程内部类，重写run方法，启动线程
        new Thread() {
            @Override
            public void  run(){
                 System.out.println("线程启动，继承方式");
             }
        }.start();





        // 匿名接口内部类重写run方法，整体作为值传入线程实例对象，启动线程
        Runnable r = new Runnable(){
            @Override
            public void run(){
                System.out.println("线程启动，接口方式");
            }
        };

        new Thread(r).start();

    }






    public static void main2(String[] args) {

        //获取主线程名称
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("ThreadName = " + name);

        interFace test = new interFace();
        Thread th = new Thread(test);
        th.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main..... " + i);
        }
    }




    public static void main1(String[] args) {
        extend t1 = new extend();
        String name = t1.getName();
        System.out.println("name = " + name);
        t1.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("main..... " + i);
        }
    }
}
