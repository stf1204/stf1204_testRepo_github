import java.util.Comparator;


public class myComparator implements Comparator<Person> {

    /*
    o1 为调用者，即要插入的对象
    o2 为被比较者，即哈希表中已经存在的对象
     */

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getAge()-o2.getAge();
    }
}
