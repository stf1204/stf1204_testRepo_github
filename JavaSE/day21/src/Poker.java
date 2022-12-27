import java.util.*;

public class Poker {

    public static void main(String[] args) {
        //存储牌和序号对应的键值对
        Map<Integer,String> map = new HashMap<>();

        //存储序号，序号为int类型，后面对序号进行操作
        List<Integer> list = new ArrayList<>();

        // 序号
        int index=0;
        String[] color = {"♣","♦","♥","♠"};
        String[] numbers = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};

        for (String num:numbers){
            for (String colo:color){
                map.put(index,colo+num);
                list.add(index);
                index++;
            }
        }
        list.add(index);
        map.put(index++,"大王");
        list.add(index);
        map.put(index++,"小王");

        // System.out.println("map = " + map)
        // System.out.println("list = " + list)


        //  洗牌，洗牌
        Collections.shuffle(list);

        // 发牌
        Set<Integer> player1 = new TreeSet<>();
        Set<Integer> player2 = new TreeSet<>();
        Set<Integer> player3 = new TreeSet<>();
        Set<Integer> diPai = new TreeSet<>();

        for (int i = 0; i <list.size(); i++) {
            if (i<3){
                diPai.add(list.get(i));
            }else if (i%3==0){
                player1.add(list.get(i));
            }else if (i%3==1){
                player2.add(list.get(i));
            }else{
                player3.add(list.get(i));
            }
        }

        // System.out.println("diPai = " + diPai)
        // System.out.println("player1 = " + player1)
        // System.out.println("player2 = " + player2)
        // System.out.println("player3 = " + player3)

        // 看牌 看牌
        lookPoker("刘德华",player1,map);
        lookPoker("梁朝伟",player2,map);
        lookPoker("郭富城",player3,map);
        lookPoker("底牌",diPai,map);
    }


    public static void lookPoker(String name, Set<Integer> set,Map<Integer,String> map){
        System.out.println(name+"::::");
        for (Integer p:set) {
            String s = map.get(p);
            System.out.print(s+" ");
        }
        System.out.println();
    }
}
