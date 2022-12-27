package com.atguigu.javase;

import java.util.Scanner;

/**
 * 用户交互Web层
 * @author stf
 */
public class AccountWeb {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入付款人：");
        String subName = sc.nextLine();
        System.out.println("请输入收款人：");
        String addName = sc.nextLine();
        System.out.println("请输入转账金额：");
        double money = sc.nextDouble();

        AccountService as = new AccountService();
        as.transfer(subName,addName,money);
    }
}
