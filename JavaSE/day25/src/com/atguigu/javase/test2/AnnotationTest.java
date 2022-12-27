package com.atguigu.javase.test2;

/**
 * @author stf
 */
public class AnnotationTest {

    @MyTest
    public void test1() {
        System.out.println("test1");
    }

    @MyTest
    public void test2() {
        System.out.println("test2");
    }

    public void test3() {
        System.out.println("test3");
    }
    @MyTest
    public void test4() {
        System.out.println("test4");
    }
}
