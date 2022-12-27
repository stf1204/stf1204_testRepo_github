import java.util.*;

public class MapDemo {

    public static void test(){
        Map<String, Integer> map = new HashMap<>();

        // put和get为map进行存储和获取的方法
        map.put("hadoop",1);
        map.put("kafka",2);
        map.put("streaming",3);
        map.put("linux",4);
        map.put("spark",5);
        map.put("flume",6);
        Integer flume = map.put("flume", 7);
        // put 带有一个Value类型的返回值，没有存相同key的时候，返回值为Null，重复存储时，返回值为被覆盖值。
        System.out.println("flume = " + flume);
        System.out.println("map = " + map);

        Integer hadoop = map.get("hadoop");
        System.out.println("hadoop = " + hadoop);
    }

    public static void test2(){
        Map <Integer,String > m2 = new HashMap<>();
        m2.put(1,"hadoop");
        m2.put(2,"flume");
        m2.put(3,"spark");
        m2.put(4,"flume");
        m2.put(5,"zookeeper");
        m2.put(6,"kafka");
        System.out.println(m2);

        // .containsKey()
        // .containsValue()
        boolean key = m2.containsKey(3);
        boolean value = m2.containsValue("HDFS");
        System.out.println("key = " + key);
        System.out.println("value = " + value);

        // .values()
        Collection<String> values = m2.values();
        System.out.println("values = " + values);
    }

    public static void test3(){
        Map<Integer,String> mm= new HashMap<>();
        mm.put(1,"name");
        mm.put(2,"age");
        mm.put(3,"gender");
        mm.put(4,"item");
        mm.put(5,"grade");
        System.out.println("mm = " + mm);

        System.out.println("===================================================");
        // Map第一种遍历方式
        //1、调用keyset() 拿到set集合
        Set<Integer> in = mm.keySet();

        //2、构建迭代器对象
        Iterator<Integer> i2 =in.iterator();

        //3、取出set集合中的元素
        while (i2.hasNext()){
            Integer key =i2.next();
            String value = mm.get(key);
            System.out.println(key +" = " + value);
        }
        System.out.println("==================================================");


        // Map第二种遍历方式
        //1、获取Map键值对的 对应关系对象
        Set<Map.Entry<Integer,String>> s1 =mm.entrySet();

        //2、构造迭代器来进行遍历
        Iterator<Map.Entry<Integer,String>> i1 = s1.iterator();

        //3、构造器遍历对应关系，通过对应关系拿到key和value
        while (i1.hasNext()){
            Map.Entry<Integer, String> n = i1.next();
            Integer key = n.getKey();
            String value = n.getValue();
            System.out.println(key + " = " + value);
        }
        System.out.println("===================================================");
    }


    public static void test4(){
        Map<Integer,String> mm= new HashMap<>();
        mm.put(1,"name");
        mm.put(2,"age");
        mm.put(3,"gender");
        mm.put(4,"item");
        mm.put(5,"grade");
        System.out.println("mm = " + mm);

        System.out.println("===================================================");

        // 增强型for循环遍历 为间接遍历
        // Map第一种间接遍历方式
        for (Integer in :mm.keySet()){
            System.out.println(in+" = " + mm.get(in));
        }
        System.out.println("==================================================");
        // Map第二种间接遍历方式
        for (Map.Entry<Integer,String> m1: mm.entrySet()){
            // System.out.println(m1.getKey() + " = " + m1.getValue())
            System.out.println(m1.getKey() + " = " + mm.get(m1.getKey()));
        }
        System.out.println("===================================================");
    }



    public static void main(String[] args) {
        test4();
    }
}
