package com.atguigu.javase;

import java.util.Objects;

public class MyDate {
    private int years;
    private int months;
    private int days;

    public MyDate() {
    }

    public MyDate(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public int getYears() {
        return years;
    }

    public int getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }


    // @Override
    // public boolean equals(Object obj){
    //     if (obj instanceof MyDate){
    //     MyDate obj1 = (MyDate) obj;  //强转
    //     if (this.days==obj1.days && this.months==obj1.months && this.years==obj1.years){
    //         return true;
    //     }
    //     }
    //     return false;
    // }


    // @Override
    // public boolean equals(Object obj){
    //     if (obj instanceof MyDate &&    //双与具有短路效果，先判断obj是否为MyDate子类
    //             this.days==((MyDate)obj).days &&    //造型
    //             this.months==((MyDate)obj).months &&    //造型
    //             this.years==((MyDate)obj).years){   //造型
    //             return true;
    //         }
    //     return false;
    // }
    //
    // @Override
    // public int hashCode() {
    //     return Integer.parseInt(years * 8 + "" + months * 24 + days *2);
    // }


    @Override
    // 用idea帮忙生成的方法
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return years == myDate.years &&
                months == myDate.months &&
                days == myDate.days;
    }

    @Override
    public int hashCode() {
        return Objects.hash(years, months, days);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "years=" + years +
                ", months=" + months +
                ", days=" + days +
                '}';
    }
}
