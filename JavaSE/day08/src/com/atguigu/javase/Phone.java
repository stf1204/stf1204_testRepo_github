package com.atguigu.javase;

public class Phone {
    private String os;
    private double screen;
    private int disk;

    public double getScreen() {
        return screen;
    }

    public void setScreen(double screen) {
        this.screen = screen;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Phone() {
    }

    public Phone(String os, double screen, int disk) {
        this.os = os;
        this.screen = screen;
        this.disk = disk;
    }

    public String say(){
        return "操作系统:"+os+",屏幕:"+screen+",磁盘:"+disk;
    }
}
