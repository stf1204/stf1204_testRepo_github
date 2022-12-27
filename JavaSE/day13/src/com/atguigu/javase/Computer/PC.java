package com.atguigu.javase.Computer;

public class PC extends Computer {
    String keyboard; //jian pan

    // 无参
    public PC() {
        super();
        // super("默认",16,128); // 调用有参
        this.keyboard = "默认";

    }

    // 全参
    public PC(String cpu, int memory, int disk, String keyboard,int price) {
        super(cpu, memory, disk,price);
        this.keyboard = keyboard;

    }

    public void code() {
        System.out.println("可以用" + keyboard + "写代码,memory:" + this.getMemory());
    }

    @Override
    public String getDetail() {   // 方法覆盖(最重要  没有之一)
        // return "CPU:"+ getCpu() +",内存:"+ getMemory() +",磁盘:"+ getDisk()+",键盘:"+keyboard;
        return super.getDetail() + ",键盘:" + keyboard;   // super特指父类
    }
}
