package homework.Home;

public abstract class Device {
    private static int total=1;
    private static final String factorys="atguigu";

    private final int id;
    private String name;
    private int price;

    public int getId() {
        return id;
    }

{ this.id = total++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getFactorys() {
        return factorys;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Device() {
    }

    public Device( String name, int price) {
        this.name = name;
        this.price = price;
    }

    public abstract void startup();
    public abstract void endup();

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
