package com.atguigu.javase.Reflect;

/**
 * 获取类对象的三种不同方法
 * @author stf
 */
public class GetClassObject {
    public static void main(String[] args) throws Exception {
        getClassNames3();
    }

    public static void getClassNames1(){

        // 1、通过实例化对象，用对象调用getClass方法，获取类对象
        Person p = new Person();
        Class cla = p.getClass();
        //pname = class com.atguigu.javase.pojo.Person
        System.out.println("cla = " + cla);

    }
    public static void getClassNames2(){

        //2、通过类名点class来获取类对象
        Class<Person> cla = Person.class;
        //pname = class com.atguigu.javase.pojo.Person
        System.out.println(cla);
    }
    public static void getClassNames3() throws Exception {

        //3、通过Class类调用forName方法来获取
        Class pname = Class.forName("com.atguigu.javase.Reflect.Person");
        //pname = class com.atguigu.javase.pojo.Person
        System.out.println("pname = " + pname);
    }
}
