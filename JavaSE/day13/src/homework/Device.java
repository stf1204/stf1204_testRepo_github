package homework;

public class Device {
    private String name;
    private double price;
    private String factory;

    public Device() {
    }

    public Device(String name, double price, String factory) {
        this.name = name;
        this.price = price;
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }
    public String say(){
        return "名称："+name+",价格："+price+",代工厂："+factory;
    }
}
