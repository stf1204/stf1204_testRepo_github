package com.atguigu.javase.Reflect;

import java.lang.reflect.Method;

/**
 * 反射获取get和set方法
 * @author stf
 */
public class GetClassMethod {
    public static void main(String[] args) throws Exception{
        getClassSetMethod();
        getClassGetMethod();
    }


    /**
     * 获取setName()方法，并执行
     * @throws Exception
     */
    public static void getClassSetMethod() throws Exception {
        Class cl = Class.forName("com.atguigu.javase.Reflect.Person");
        Object o = cl.newInstance();

        /*
        //获取被反射的所有公共权限方法，包括继承的类和实现的接口的方法
        //返回是Method数组
        Method[] methods = cl.getMethods();
        for (Method me:methods){
            System.out.println("me = " + me);
        }
        */

        Method sm = cl.getMethod("setName", String.class);
        System.out.println("Set方法 = " + sm);
        // 反射执行的方法 invoke(对象，调用方法的实际参数)
        sm.invoke(o,"张三");
        System.out.println("o = " + o);

    }



    /**
     * 获取getName()方法，获取名字
     * @throws Exception
     */
    public static void getClassGetMethod() throws Exception{
        // 获取获取类对象
        Class cl = Class.forName("com.atguigu.javase.Reflect.Person");
        // 获取实例化对象
        Object o = cl.newInstance();

        //获取getName()方法
        Method me = cl.getMethod("getName");
        System.out.println("Get方法 = " + me);

        //执行方法
        Object o1 = me.invoke(o);
        System.out.println("o1 = " + o1);
    }
}
