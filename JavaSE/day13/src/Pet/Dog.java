package Pet;

public class Dog extends Pet {
    private String pin_zhong;

    @Override
    public String toString() {
        return super.toString()+
                "pin_zhong='" + pin_zhong + '\'' ;
    }

    @Override
    public void speak() {

    }

    @Override
    public void eat() {

    }

    public String getPin_zhong() {
        return pin_zhong;
    }

    public void setPin_zhong(String pin_zhong) {
        this.pin_zhong = pin_zhong;
    }

    public Dog() {
    }

    public Dog(String name, int age, double weight, String pin_zhong) {
        super(name, age, weight);
        this.pin_zhong = pin_zhong;
    }
}
