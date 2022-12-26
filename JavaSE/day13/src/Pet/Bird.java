package Pet;

public class Bird extends Pet {
    private double flySpeed;


    public Bird(String name, int age, double weight, double flySpeed) {
        super(name, age, weight);
        this.flySpeed = flySpeed;
    }

    public Bird() {
    }

    @Override
    public String toString() {
        return super.toString() +
                "flySpeed=" + flySpeed;
    }

    public double getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(double flySpeed) {
        this.flySpeed = flySpeed;
    }

    @Override
    public void eat() {

    }

    @Override
    public void speak() {

    }

}
