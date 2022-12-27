package com.atguigu.javase.deadLock;

public class deadLockTest {

    public static void main(String[] args) {

        DeadLock True = new DeadLock(true);
        DeadLock False = new DeadLock(false);

        new Thread(True).start();
        new Thread(False).start();
    }
}
