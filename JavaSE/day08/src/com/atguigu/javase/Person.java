package com.atguigu.javase;

public class Person {
    private String name;
    private int age;
    private int height;
    private Phone phone;  //对象关联

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

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

    public Person(String name, int age, int height,Phone phone) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.phone =phone;
    }

    public String say(){
        return "姓名:"+name+"年龄:"+age+"身高"+height+",用的手机是"+phone;
    }

    public void game(){
        System.out.println(name+"在玩["+phone.say()+"]中的王者荣耀");
    }
    public void shop(){
        System.out.println(name+"在用["+phone.say()+"]购物");
    }
}

