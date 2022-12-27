package com.atguigu;

import org.junit.Test;

public class MyTest {
    @Test
    public void testMyMath() {
        int sum = MyMath.getSum(3, 6);
        System.out.println("sum = " + sum);
    }
}
