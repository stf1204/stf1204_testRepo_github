package homework.Home;

public class Frock {
    //静态属性
    private static int currentNum=100000;
    // 公有静态常量
    public  static  final int INCREMENT;

    //非静态属性
    private int size;
    private String color;
    private double price;
    private final int serialNumber;

    // 静态语句块  是对类初始化的补充
    static {
        INCREMENT=200;
        Frock.currentNum=150000;
        System.out.println(currentNum);
    }

    // 非静态语法块  是对构造器的补充，和构造器最后整合到一个方法内才进行执行
    {
        System.out.println("hello 我是非静态语法块");
        this.serialNumber=getNextNum();     //与构造器无关，每次实例化对象都会执行。。适合存放那些new对象就一定要执行的代码块
    }

    // 静态方法
    public static int getNextNum(){
        currentNum=currentNum+INCREMENT;
        return currentNum;
    }
    public Frock() {
        // this.serialNumber=getNextNum();
    }

    public Frock(int size, String color, double price) {
        this.size = size;
        this.color = color;
        this.price = price;
        // this.serialNumber = getNextNum();
    }

    public double getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Frock{" +
                "size=" + size +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
