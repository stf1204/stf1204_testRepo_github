package Pet;

public abstract class Pet {
    private String name;
    private int age;
    private double weight;

    public abstract void speak();
    public abstract void eat();

    @Override
    public String toString() {
        return "Pet:" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight+"  ";
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Pet() {
    }

    public Pet(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }
}
