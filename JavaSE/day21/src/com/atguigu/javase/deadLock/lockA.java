package com.atguigu.javase.deadLock;

public class lockA {

    private lockA(){}

    public static final lockA lockA = new lockA();
}
