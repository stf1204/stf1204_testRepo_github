package com.atguigu.javase.polymorhpysm;

public class NotePad extends Computer {
    String wei_xing;

    // 无参
    public NotePad() {
        super();
        // super("默认",32,128); // 调用有参
        this.wei_xing = "默认";

    }

    // 全参
    public NotePad(String cpu, int memory, int disk, String wei_xing,int price) {
        super(cpu, memory, disk,price);
        this.wei_xing = wei_xing;

    }


    public void guide() {
        System.out.println("可以用" + wei_xing + "导航,cpu:" + this.getCpu());
    }

    @Override //特殊的注释 可以被编译器和JVM识别
    // 该注解的作用是说明这个方法是要被覆盖，请编译器帮忙检查覆盖条件。
    public String getDetail() {   // 方法覆盖
        // return "CPU:"+ getCpu() +",内存:"+ getMemory() +",磁盘:"+ getDisk()+",卫星:"+wei_xing;
        return super.getDetail() + ",卫星:" + wei_xing;
        // super特指父类
        //  super调用便于父类的修改 对子类没有影响


    }

}

