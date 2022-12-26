package com.atguigu.javase.homework;

public class DeviceTest {
    public static void main(String[] args) {

            /*
        写一个类Device, 表示设备, 属性,String name, double price, 至少要有say(). 属性要封装
        写子类InputDevice, 没有属性和方法
        写子类Mouse是InputDevice的子类, 特有属性 : String color, 特有方法, public void move()
        在测试类中创建鼠标对象, 为所有属性赋值, 打印输出对象的详细信息
     */

        Mouse m = new Mouse();
        m.setName("罗技G5");
        m.setPrice(200);
        m.setColor("蓝色");
        System.out.println(m.say());
        System.out.println(m.getColor());
        m.move();
    }
}
