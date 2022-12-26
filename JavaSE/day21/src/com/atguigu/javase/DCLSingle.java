package com.atguigu.javase;

/**
 DCL单例模式(Double Check Lock)
 双if 检查机制
 * @author stf
 */
public class DCLSingle {

    /**
     1、私有构造器
     */
    private DCLSingle(){}

    /**
     * 2、私有单例对象
      */
    private static DCLSingle d1 = null;

    /**
     3、公共获取对象方法
     */
    public static DCLSingle getInstance(){
        if (d1 == null){
            synchronized (DCLSingle.class){
                if (d1==null){
                    d1 = new DCLSingle();
                }
            }
        }
        return d1;
    }
}
