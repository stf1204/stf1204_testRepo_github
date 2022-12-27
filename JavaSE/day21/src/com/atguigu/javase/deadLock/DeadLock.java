package com.atguigu.javase.deadLock;

/**
 * 死锁实现，考察同步synchronized
 * @author stf
 */
public class DeadLock implements Runnable{

    private boolean flag;

    public DeadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true){
            // 先A后B锁
            if (flag){
                synchronized (lockA.lockA){
                    System.out.println("if....lockA");
                    synchronized (lockB.lockB){
                        System.out.println("if....lockB");
                    }
                }
            }
            // 先B后A锁
            else{
                synchronized (lockB.lockB){
                    System.out.println("else...lockB");
                    synchronized (lockA.lockA){
                        System.out.println("else...lockA");
                    }
                }
            }
        }
    }
}
