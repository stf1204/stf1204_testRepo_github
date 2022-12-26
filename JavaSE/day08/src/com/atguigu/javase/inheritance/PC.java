package com.atguigu.javase.inheritance;

public class PC extends Computer {
    String keyboard; //jian pan

    public void code(){
        System.out.println("可以用"+keyboard+"写代码,memory:"+ this.getMemory());
    }

    @Override
    public String getDetail(){   // 方法覆盖(最重要  没有之一)
        // return "CPU:"+ getCpu() +",内存:"+ getMemory() +",磁盘:"+ getDisk()+",键盘:"+keyboard;
        return super.getDetail()+",键盘:"+keyboard;   // super特指父类
    }
}
