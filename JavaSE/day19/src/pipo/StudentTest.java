package pipo;

import java.util.*;

class ComparatorTest implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        // o1 为调用者，要存储的新对象
        // o2 为被比较者，即已经存在于TreeSet中的对象
        return o1.getAge()-o2.getAge();
    }
}

public class StudentTest {

    public  static  void TreeSet2(){
        Set<Student> set = new TreeSet<>(new ComparatorTest());
        set.add(new Student("张三",29));
        set.add(new Student("李四",26));
        set.add(new Student("王五",24));
        for (Student student : set) {
            System.out.println(student);
        }
    }


    public  static  void TreeSet(){
        Set<Student> set = new TreeSet<>();
        set.add(new Student("张三",29));
        set.add(new Student("李四",26));
        set.add(new Student("王五",24));
        for (Student student : set) {
            System.out.println(student);
        }
    }

    public static void HashSet(){
        Set<Student> hash = new HashSet<>();
        hash.add(new Student("张三",29));
        // 重复的李四不会被重复记载  重写的hashcode 和equals 方法判断二者相同
        hash.add(new Student("李四",26));
        hash.add(new Student("李四",26));
        hash.add(new Student("王五",24));
        for (Student student : hash) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {

        TreeSet2();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        TreeSet();
        System.out.println("============");
        HashSet();
        System.out.println("============");
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三",32));
        list.add(new Student("李四",23));
        list.add(new Student("王五",33));

        Iterator<Student> it =list.iterator();
        for (; it.hasNext();) {
            Student s1 = it.next();
            System.out.println("s1 = " + s1);

        }
    }
}
