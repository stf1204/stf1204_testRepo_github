package homework.Home;

public class Mouse extends Device {
    public Mouse() {
    }

    public Mouse(String name, int price) {
        super(name, price);
    }

    @Override
    public void startup() {
        System.out.println("启动中....");
    }

    @Override
    public void endup() {
        System.out.println("关闭中....");
    }
}
