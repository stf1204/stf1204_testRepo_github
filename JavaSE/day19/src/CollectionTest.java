import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTest {

    public static void IteratorDemo(){
        Collection<String> col = new ArrayList<String>();
        col.add("hello");
        col.add("你好");
        col.add("我好");
        col.add("hadoop");

        //1、new 对象
        Iterator<String> i1 = col.iterator();
        // //2、是否有next
        // boolean b = i1.hasNext();
        // System.out.println(b);
        // //3、true的话就next
        // System.out.println(i1.next());

        while (i1.hasNext()){
            System.out.println(i1.next());
        }
        // 迭代器经过while循环以后 指针已经走到最后了，不能再次使用了
        // 新建迭代器才可以
        for (Iterator <String> i2=col.iterator();i2.hasNext();){
            System.out.println(i2.next());
        }
        // for 比while更节约内存，因为i2迭代器作用域在for内 用完即删除，而while在方法中，用完还一直占用内存
    }

    public static void CollectionTest(){
        Collection<String> col = new ArrayList<String>();
        col.add("hello");
        col.add("你好");
        col.add("我好");
        col.add("hadoop");

        // 将集合中存储的数据打出，不是遍历
        System.out.println(col);

        // contains 判断某元素在不在集合中
        boolean b = col.contains("java");
        System.out.println("b = " + b);

        //清空集合元素
        col.clear();
        System.out.println(col);

        // 判断集合是否为空
        boolean b1 = col.isEmpty();
        System.out.println("b1 = " + b1);


        // 集合长度
        int size = col.size();
        System.out.println("size = " + size);

        // java 中获取长度方式到齐
        // 1、数组.length
        // 2、字符串.length()
        // 3、集合.size()




    }



    public static void main(String[] args) {
        // CollectionTest();
        IteratorDemo();
    }


}
