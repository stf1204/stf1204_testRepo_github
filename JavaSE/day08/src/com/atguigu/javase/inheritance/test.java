package com.atguigu.javase.inheritance;

public class test {
    public static void main(String[] args) {
        PC pc = new PC();

        pc.setCpu("三星");
        pc.setDisk(128);
        pc.setMemory(8);
        pc.keyboard="可达鸭";

        System.out.println("disk="+ pc.getDisk());
        System.out.println("memory="+ pc.getMemory());
        System.out.println("cpu="+ pc.getCpu());
        pc.code();
        String detail = pc.getDetail();
        System.out.println("PC = " + detail);
        System.out.println("---------------------------");

        NotePad np =new NotePad();

        np.wei_xing = "北斗卫星";
        np.setCpu("三星");
        np.setDisk(256);
        np.setMemory(16);

        System.out.println("memory = " + np.getMemory());
        System.out.println("disk = " + np.getDisk());
        System.out.println("cpu = " + np.getCpu());
        np.guide();
        String s =np.getDetail();
        System.out.println("NotePad = " + s);
        System.out.println("*********************");
    }
}
