import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("Java");
        list1.add("Jdk");
        list1.add("Hadoop");
        list1.add("Linux");
        System.out.println(list1);
        System.out.println("________________________________________________");

        //因为队列有下标，所以可以通过get() size()方法来对队列集合进行遍历
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i));
        }
        System.out.println("___________________________________________________");

        // 迭代器遍历
        //while 遍历不如 for
        Iterator<String> i1 = list1.iterator();
        while (i1.hasNext()){
            System.out.println(i1.next());
        }
        System.out.println("________________________________________________");

        list1.add(1,"Flume");
        list1.add(4,"Flink");

        // for 遍历
        for (Iterator <String> i2 =list1.iterator(); i2.hasNext(); ) {
            System.out.println(i2.next());
        }
        System.out.println("________________________________________________");

        // get获取指定索引下标的元素   越界会异常
        String s = list1.get(4);
        System.out.println("s = " + s);
        System.out.println("________________________________________________");

        // set修改指定下标的元素，并返回修改之前的元素  越界会异常
        String s2 = list1.set(4, "flink");
        System.out.println("s2 = " + s2);
        System.out.println("list1 = " + list1);
        System.out.println("________________________________________________");

        // remove删除指定下标的元素并返回删除的元素  越界会异常
        // 传入的是基本数据类型就按照下标来删除，传入对象按照集合有没有 来删除
        String r1 = list1.remove(4);

        // boolean s22 = list1.remove(new Interger(1))
        // 当存入的类型是interger类型时候，需要删除某元素需要按照对象来删除，否则会按照下标删
        System.out.println("r1 = " + r1);
        System.out.println("list1 = " + list1);
        System.out.println("________________________________________________");
    }
}
