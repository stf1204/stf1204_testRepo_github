package com.atguigu.javase.javabean;

public class Point {

    /*
     3.定义一个“点”类用来表示平面中中的点（有两个坐标）。要求如下：
    1）可以生成具有特定坐标的点对象。
    2）提供可以设置两个坐标的方法。
    3）提供可以计算该“点”距原点距离的平方的方法

     */

    private double x0;
    private double x1;

    //构造
    public Point() {
        this(0, 0);
    }

    public Point(double x0, double x1) {
        this.x0 = x0;
        this.x1 = x1;
    }

    //get set
    public void setX0(double x0) {
        this.x0 = x0;
    }

    public void setX1(double x1) {
        this.x0 = x1;
    }

    public double getX0() {
        return x0;
    }

    public double getX1() {
        return x1;
    }

    public double getDistance() {
        double s = x0 * x0 + x1 * x1;
        return s;
    }

    public void say() {
        System.out.println("x0:" + x0 + ",x1:" + x1);
    }
}
