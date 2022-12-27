package homework;

public class Single2 {

    private String name;
    private int age;
    private String gender;
    private static Single2 s2;

    private  Single2(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    private Single2() {
    }

    // 获取唯一实例对象
    public static Single2 getS2(){
        if (s2!=null){
            return s2;
        }
        s2 = new Single2("小明",32,"男");
        return s2;
    }

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
        return "Single2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
