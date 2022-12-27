package pipo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
扑克牌案例
 */

public class Poker {

    public static void main(String[] args) {

        // 准备牌
        String [] colors ={"♠","♥","♣","♦"};
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        List<String> link = new ArrayList<>();

        for (String color : colors) {
            for (String number : numbers) {
                // System.out.println(color+number)
                link.add(color+number);
            }
        }
        link.add("大王");
        link.add("小王");
        System.out.println("link = " + link);

        // 洗牌 使用 java.util.collections类中的shuffle()  对集合中元素进行随机排列
        Collections.shuffle(link);
        System.out.println("random_link = " + link);

        System.out.println("------------------------------------------------------------------------------");

        // 发牌
        List<String> player1 = new ArrayList<>();
        List<String> player2 = new ArrayList<>();
        List<String> player3 = new ArrayList<>();
        List<String> kanpai = new ArrayList<>();

        for (int i = 0; i < link.size(); i++) {
            if (i<=2){
                kanpai.add(link.get(i));
            }else if (i%3==0){
                player1.add(link.get(i));
            }else if (i%3==1){
                player2.add(link.get(i));
            }else{
                player3.add(link.get(i));
            }
        }

        // 看牌
        look(player1);
        look(player2);
        look(player3);
        look(kanpai);
    }

    public static void look(List list){
        for (Object o : list) {
            System.out.print(o+" ");
        }
        System.out.println();
    }
}
