package com.atguigu.javase.c;

public class Computer {
    private String cpu;
    private int memory;
    private int disk;

/*
    this.属性和方法   可以先访问本类中的方法和属性，如果没有，会去父类中找该方法和属性。
    super.属性和方法   直接访问父类中的属性和方法

    构造器：
    this(...) 会间接调用父类构造器（通过本类中的方法进行调用）
    super(...)  会直接调用父类构造器（调用直接父类方法）
    结论：子类构造器一定会先 调用父类构造器。
    this表示当前对象，super无此概念。

 */

    // 无参
    public Computer() {
        System.out.println("com...");
    }

    public Computer(String cpu) {
        this(cpu, 16, 128);
        // this.cpu = cpu;
    }

    public Computer(String cpu, int memory) {
        this(cpu, memory, 128);

        // this.cpu = cpu;
        // this.memory = memory;
    }

    // 全参
    public Computer(String cpu, int memory, int disk) {
        this.cpu = cpu;
        this.memory = memory;
        this.disk = disk;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public String getDetail() {   // 虚拟方法调用
        return "CPU:" + cpu + ",内存:" + memory + ",磁盘:" + disk;
    }

}
