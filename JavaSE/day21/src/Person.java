public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public void Person (){}

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

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Person person = (Person) o;

        if (age != person.age) {return false;}
        return name != null ? name.equals(person.name) : person.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    /**
     *  this 为调用者，即需要存入的元素
     *  p 为被比较的对象，哈希表中已经存在的对象
     */
    public int compareTo(Person p){
        return this.age-p.age;
    }

}
