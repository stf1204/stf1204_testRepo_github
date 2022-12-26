package com.atguigu.javase.test2;

import java.lang.reflect.Method;

/**
 * @author stf
 * 执行带有MyTest注解的方法
 */
public class Test {
    public static void main(String[] args) throws Exception{

        //反射创建类对象，反射创建实例对象
        Class cla = Class.forName("com.atguigu.javase.test2.AnnotationTest");
        Object o = cla.newInstance();

        //获取类所有方法，进行判断
        Method[] methods = cla.getMethods();
        for (Method method:methods){
            // System.out.println("method1 = " + method)
            boolean b = method.isAnnotationPresent(MyTest.class);
            // System.out.println("b = " + b)
            if (b){
                method.invoke(o);
            }
        }
    }
}
