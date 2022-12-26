package pipo;

import java.util.*;

/**
 * @author stf
 */
public class TestStringPerson {

    public static void stringPersonTest(){
        Map<String,Person> m = new HashMap<>();

        m.put("北京",new Person("小明",22));
        m.put("山西",new Person("小丽",18));
        m.put("上海",new Person("小胖",19));
        m.put("杭州",new Person("小芳",19));
        m.put("南京",new Person("小欧",21));

        Set<Map.Entry<String,Person>> m1 = m.entrySet();

        Iterator<Map.Entry<String,Person>> ship = m1.iterator();

        while (ship.hasNext()){
            Map.Entry<String,Person> i2 = ship.next();
            String key = i2.getKey();
            Person value = i2.getValue();
            System.out.println(key + " = " + value);
        }
    }

    public static void personStringTest(){
        Map <Person,String> ps = new HashMap<>();

        ps.put(new Person("小芳",21),"北京");
        ps.put(new Person("小丽",23),"南京");
        ps.put(new Person("小美",22),"上海");
        ps.put(new Person("小红",20),"杭州");
        ps.put(new Person("小宋",19),"浙江");

        Set<Map.Entry<Person,String>> mnps = ps.entrySet();

        Iterator <Map.Entry<Person,String>> ips = mnps.iterator();

        while (ips.hasNext()){
            Map.Entry<Person,String> meps = ips.next();
            Person key = meps.getKey();
            String value = meps.getValue();
            System.out.println(key+ " = " + value);
        }
    }


    public static void main(String[] args) {
        personStringTest();
        stringPersonTest();
    }
}
