package com.atguigu.javase.homework;

public class Mouse extends InputDevice {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Mouse() {
    }

    public Mouse(String color) {
        this.color = color;
    }

    public Mouse(String name, double price, String color) {
        super(name, price);
        this.color = color;
    }

    public void move(){
        System.out.println("i can move");
    }

    @Override
    public String say(){
        return super.say()+"颜色:"+color;
    }
}
