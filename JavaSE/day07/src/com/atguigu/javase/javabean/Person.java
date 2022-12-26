package com.atguigu.javase.javabean;

public class Person {
    private String name;
    private int age;
    private int height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Person() {
    }

    public Person(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String say(){
        return "姓名:"+name+"年龄:"+age+"身高"+height;
    }

    public void play(Phone phone){
        System.out.println(name+"在玩["+phone.say()+"]中的王者荣耀");
    }
}


