package com.atguigu.javase.polymorphism;

import java.util.Objects;

public class Point {
    private double x;
    private double y;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // @Override
    // public boolean equals(Object obj){
    //     if (obj instanceof Point) {
    //         Point obj1 = (Point) obj;
    //         if (this.x == obj1.x && this.y == obj1.y) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // @Override
    // public boolean equals(Object obj) {   //会有多态副作用
    //     if (obj instanceof Point &&  //先判断是否父类实例化对象  &&具有短路效果
    //             this.x == ((Point) obj).x &&     // obj需要造型
    //             this.y == ((Point) obj).y) {     // obj需要造型
    //         return true;
    //     }
    //     return false;
    // }
    //
    // // @Override
    // // public int hashCode() {
    // //     return Integer.parseInt(x * 10 + "" + y * 10);
    // // }
    //
    // @Override
    // public int hashCode() {
    //     return (int) (x*1007+y*302);
    // }


    @Override
    // 用idea自动生成的方法
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "x:" + x + ",y:" + y;
    }
}
