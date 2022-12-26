package com.atguigu.javase.polymorhpysm;

public class test {

    public static void main(String[] args) {
        Computer[] com = new Computer[8];
        Computer san_xiong = new Computer("san xiong", 22, 22,111);
        Computer san_xi = new Computer("san xiong", 22, 22,222);
        Computer pc = new PC("san xoin", 12, 12, "ke",333);
        Computer pc1 = new PC("san xoin", 12, 12, "ke",5455);
        Computer pc2 = new PC("san xoin", 12, 12, "ke",123);
        Computer notePad = new NotePad("sanxin", 22, 51, "cha",231);
        Computer notePad1 = new NotePad("sanxin", 22, 51, "cha",512);
        Computer notePad2 = new NotePad("sanxin", 22, 51, "cha",512);
        com[0]=san_xiong;
        com[1]=san_xi;
        com[2]=pc1;
        com[3]=pc2;
        com[4]=notePad;
        com[5]=notePad1;
        com[6]=notePad2;
        com[7]=pc;

        for (Computer computer : com) {
            System.out.println(computer.getDetail());
        }


        /*
        //冒泡排序
        for (int i = 0; i < com.length-1; i++) {
            for (int j = 0; j < com.length-1-i; j++) {
                if (com[j].getPrice()>com[j+1].getPrice()){
                    Computer tmp=com[j];
                    com[j]=com[j+1];
                    com[j+1]=tmp;
                }
            }
        }

        System.out.println("*******************************");
        for (Computer computer : com) {
            System.out.println("computer = " + computer.getDetail());
        }

        */





        System.out.println("******************************************");
        // //选择排序
        // for (int i = 0; i < com.length-1; i++) {
        //     int index=i;
        //     for (int j = i+1; j < com.length; j++) {
        //         if (com[j].getPrice()<com[index].getPrice()){
        //             index=j;
        //         }
        //     }
        //     Computer tmp = com[i];
        //     com[i]=com[index];
        //     com[index]=tmp;
        // }

        // 选择排序
        for (int i = 0; i < com.length-1; i++) {
            int index=i;
            for (int j = i+1; j < com.length; j++) {
                if (com[j].getPrice()<com[index].getPrice()){
                    index=j;
                }
            }
            Computer tmp= com[index];
            com[index]=com[i];
            com[i]=tmp;
        }

        for (Computer computer : com) {
            System.out.println(computer.getDetail());
        }



    }



    public static void main3(String[] args) {
        Computer[] com = new Computer[8];
        Computer san_xiong = new Computer("san xiong", 22, 22,111);
        Computer san_xi = new Computer("san xiong", 22, 22,222);
        Computer pc = new PC("san xoin", 12, 12, "ke",333);
        Computer pc1 = new PC("san xoin", 12, 12, "ke",5455);
        Computer pc2 = new PC("san xoin", 12, 12, "ke",123);
        Computer notePad = new NotePad("sanxin", 22, 51, "cha",231);
        Computer notePad1 = new NotePad("sanxin", 22, 51, "cha",512);
        Computer notePad2 = new NotePad("sanxin", 22, 51, "cha",512);
        Computer notePad3 = new NotePad("sanxin", 22, 51, "cha",213);

        com[0]=san_xiong;
        com[1]=san_xi;
        com[2]=pc1;
        com[3]=pc2;
        com[4]=notePad;
        com[5]=notePad1;
        com[6]=notePad2;
        com[7]=notePad3;

        for (Computer c : com) {
            System.out.println(c.getDetail());
        }

    }
    public static void main2(String[] args) {
        Computer pc = new PC("sanxing", 11, 11, "sanxing",11);
        System.out.println(pc.getDetail());
        System.out.println("*******************");
        Computer notePad = new NotePad("sanxing", 11, 11, "beidou",22);
        System.out.println(notePad.getDetail());

    }

    public static void main1(String[] args) {
        new PC();
        System.out.println("*********");
        new PC("三星",16,128,"罗技",11000);
        System.out.println("*********");
        new NotePad();
        System.out.println("*********");
        new NotePad("三星",16,128,"北斗",2000);
    }
}
