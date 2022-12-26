package com.atguigu.javase.polymorphism;

public class PersonTest {

    public static void main(String[] args) {
        Chinese ch = new Chinese("xiao ming", 22, "男", "猪");
        American am = new American("Jack", 22, "male", true);
        Person p = new Person("小南", 19, "女");
        Peking pk = new Peking();
        System.out.println("**************");
        say(ch);
        System.out.println("---------------");
        say(am);
        System.out.println("---------------");
        say(p);
        System.out.println("---------------");
        say(pk);
        System.out.println("---------------");
    }

    public static void say(Person p){
        // 造型有风险，必须要判断
        // p instanceof chinese 判断p指向的对象实体，是否是chinese类型的一个实例
        //  对象类型的判断需要从子类开始，否则会在父类截断
        System.out.println(p.say());  // p.say() 父类方法，可以直接调用

        if(p instanceof Peking){
            ((Peking)p).PlayBird();
        }else if (p instanceof Chinese){
            Chinese c =(Chinese)p;  //强转再调用  称为造型
            c.spring();
        } else  if (p instanceof American){
            ((American)p).shoot(); // 造型再调用
        }else{
            System.out.println("我是普通人");
        }
    }
}
