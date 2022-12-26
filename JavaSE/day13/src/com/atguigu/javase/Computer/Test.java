package com.atguigu.javase.Computer;

public class Test {
    public static void main(String[] args) {

        Computer s = new Computer("computer", 12, 12, 128);
        NotePad n = new NotePad("notepad", 12, 21, "wei xing", 312);
        PC pc = new PC("PC", 129, 21, "keyboard", 21);
        listPrice(s);
        listPrice(n);
        listPrice(pc);
    }

    // 多态方法
    public  static  void listPrice(Computer c){
        System.out.println("价格："+c.getPrice());
        if (c instanceof PC){
            ((PC) c).code();
        }else if (c instanceof NotePad){
            ((NotePad) c).guide();
        }else {
            System.out.println("普通计算机");
        }
    }
}
