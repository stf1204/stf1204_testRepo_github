package com.atguigu.javase.javabean;
public class Teacher {

    private String name = "某老师";  // 属性的显示赋值
    private int age = 20;
    private String gender;


    public Teacher() {   //无参构造器
        // this.name="张三";
        // this.age=18;
        // this.gender="女";
        this("张三", 22,"男");

        System.out.println("我是无参构造器，是进行初始化操作的");
    }

    // public Teacher(String name, int age) {
    //     // this.name = name;
    //     // this.age =age;
    //     // this.gender="男";
    //     this(name, age, "男");
    //
    //     System.out.println("String int");
    // }

    public Teacher(String name, int age, String gender) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        System.out.println("String int String");
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
        if (age < 0 || age > 130) { //不合法
            return; //提前弹栈
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void lesson() {
        System.out.println(name + "老师在上课"); // 成员互访
    }

    public String say() {
        String s = "姓名:" + name + ",性别:" + gender + ",年龄:" + age;
        return s;
    }

    public void eat(String something) {
        System.out.println(name + "老师在吃" + something);
    }
}
