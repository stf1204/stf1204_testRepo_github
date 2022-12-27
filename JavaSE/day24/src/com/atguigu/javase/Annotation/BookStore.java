package com.atguigu.javase.Annotation;

/**
 * Deprecated 过时的
 * @author stf
 */

@Deprecated
public class BookStore {

    @bookInterface(name="红楼梦",price = 22.1,authors = {"曹雪芹","高鹗"},count = 150)
    public static void read(){
        System.out.println("true = " + true);
    }
}
