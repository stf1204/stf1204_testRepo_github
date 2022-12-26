package com.atguigu.javase.Reflect;

import java.lang.reflect.Constructor;

/**
 * 获取构造函数
 * @author stf
 */
public class GetClassConstructors {
    public static void main(String[] args) throws Exception{
        getReflectAllArgsConstructor();
    }

    /**
     * 获取无参构造函数
     * @throws Exception
     */
    public static void getReflectNoArgsConstructor() throws Exception {

        //1、Class的ForName方法，获取类对象
        Class cla = Class.forName("com.atguigu.javase.Reflect.Person");

        //2、获取指定的构造方法
        // getConstructor(构造方法的参数列表)
        Constructor constructor = cla.getConstructor();

        //3、打印构造方法
        System.out.println("constructor = " + constructor);

    }

    /**
     * 获取全参构造器
     * @throws NoSuchMethodException
     */
    public static void getReflectAllArgsConstructor() throws NoSuchMethodException {

        Class<Person> acla = Person.class;
        //getConstructor(传入需要获取的构造器的参数列表.class)
        Constructor<Person> con = acla.getConstructor(String.class, int.class);
        System.out.println("con = " + con);
    }


    /**
     * 获取所有公共构造方法
     */
    public static void getReflectConstructors(){
        //1、获取类对象
        Class<Person> pClass = Person.class;

        //2、通过类对象获取类中所有的公共限定的构造方法
        Constructor<?>[] constructors = pClass.getConstructors();

        //3、遍历打印构造方法名
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor = " + constructor);
        }
    }
}
