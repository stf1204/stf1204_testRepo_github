package com.atguigu.javase.inheritance;

public class Computer {
    private String cpu;
    private int memory;
    private int disk;

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

    public String getDetail(){
        return "CPU:"+cpu+",内存:"+memory+",磁盘:"+disk;
    }

}
