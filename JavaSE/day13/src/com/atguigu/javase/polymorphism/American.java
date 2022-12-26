package com.atguigu.javase.polymorphism;

public class American extends Person {
    private boolean hasgun;

    public boolean isHasgun() {
        return hasgun;
    }

    public void setHasgun(boolean hasgun) {
        this.hasgun = hasgun;
    }

    public American(boolean hasgun) {
        this.hasgun = hasgun;
    }

    public American(String name, int age, String gender, boolean hasgun) {
        super(name, age, gender);
        this.hasgun = hasgun;
    }
    public void shoot(){
        System.out.println("i am not happy shoot you");
    }

    @Override
    public void sayhello(){
        System.out.println("how do you do");
    }

    @Override
    public String say(){
        return super.say()+",有枪咩："+hasgun;
    }
}
