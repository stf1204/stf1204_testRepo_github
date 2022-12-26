package com.atguigu.javase.test2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author stf
 */
//元注解 指挥MyTest，生命周期是内存
//元注解 指挥MyTest，只能存在于方法上
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyTest { }


