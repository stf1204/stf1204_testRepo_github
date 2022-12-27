package com.atguigu.pojo;

import lombok.Data;

/**
 * @author stf

 @NoArgsConstructor  无参构造
 @AllArgsConstructor 全参构造
 @data 自动添加get、set、toString

 */

@Data
@SuppressWarnings("all")
public class Product {
    private Integer pid;
    private String pname;
    private Double price;
    private Integer num;
    private Integer category;
}
