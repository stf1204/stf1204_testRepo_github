package com.atguigu.javase.homework;

public class Device {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Device() {
    }

    public Device(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public  String say(){
        return "设备名:"+name+",价格:"+price;
    }
}
