import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class personStringTest {
    public static void main(String[] args)  {
        // 比较器比较
        Map<Person,String> map = new TreeMap<>(new myComparator());

        // comparable.compareTo()比较
        // Map<Person,String> map = new TreeMap<>();

        map.put(new Person("小工",22),"北京市");
        map.put(new Person("小丽",24),"西安市");
        map.put(new Person("小芳",21),"上海市");
        map.put(new Person("小名",32),"南京市");
        map.put(new Person("小崽",12),"东京市");

        Set<Map.Entry<Person,String>> meps = map.entrySet();

        Iterator <Map.Entry<Person,String>>it = meps.iterator();

        while (it.hasNext()){
            Map.Entry<Person,String> entry = it.next();
            System.out.println(entry.getKey() + " === " + entry.getValue());


        }
    }
}
