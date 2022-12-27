package Serialize;

import java.io.Serializable;

/**
 * 类必须是Serializable的实现类，才能实现序列化和反序列化功能
 * static静态修饰的成员是属于类体的，也无法进行序列化和反序列化
 * transient 瞬态的意思，阻止成员的数据序列化
 * @author stf
 */
public class Person implements Serializable {
    private int age;
    public String name;
    private String gender;
    //自定义序列号
    static final long serialVersionUID = 42L;

    public Person() {
    }

    public Person(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
