package homework;


// 写出饿汉和懒汉式单例.
public class Single {

    // 类属性
    private static Single last = new Single("小红",22,"女");   // 饿汉

    // 对象属性
    private String name;
    private int age;
    private String gender;

    //构造器
    private  Single() {

    }
    private  Single(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

///////////////////////////类方法/////////////////////////////////////////////////////
    // 获取唯一实例化对象
    public  static Single getNum(){
        return last;
    }

    public static Single getLast() {
        return last;
    }

    public static void setLast(Single last) {
        Single.last = last;
    }

    ///////////////////////对象方法/////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;

    }

    @Override
    public String toString() {
        return "Single{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }






}


