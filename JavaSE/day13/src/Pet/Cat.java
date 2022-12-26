package Pet;

public class Cat extends Pet {
    private String color;

    public Cat() {
    }

    public Cat(String name, int age, double weight, String color) {
        super(name, age, weight);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void eat() {

    }

    @Override
    public void speak() {

    }

    @Override
    public String toString() {
        return super.toString() +
                "color='" + color + '\'' ;
    }
}
