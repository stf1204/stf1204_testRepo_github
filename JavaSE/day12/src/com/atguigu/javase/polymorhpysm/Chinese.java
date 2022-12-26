package com.atguigu.javase.polymorhpysm;

public class Chinese extends Person {
    private String shuxiang;

    public Chinese() {
    }

    public Chinese(String name, int age, String gender,String shuxiang) {
        super(name, age, gender);
        this.shuxiang=shuxiang;
    }

    public String getShuxiang() {
        return shuxiang;
    }

    public void setShuxiang(String shuxiang) {
        this.shuxiang = shuxiang;
    }

    public void spring(){
        System.out.println("欢欢喜喜过大年");
    }
    @Override
    public String say(){
        return super.say()+"属性："+shuxiang;
    }
    @Override
    public void sayhello(){
        System.out.println("吃了吗？");
    }

}
