package interfaceTest;

public class Mouse extends Device implements USB {


    @Override
    public void startup() {
        System.out.println("start...");
    }

    @Override
    public void shoutdown() {
        System.out.println("shoutdown...");
    }

    @Override
    public void connect() {
        System.out.println("connect...");
    }

    @Override
    public void work() {
        System.out.println("work...");
    }

    @Override
    public void disconnect() {
        System.out.println("disconnect...");
    }

    public Mouse(String name) {
        super(name);
    }
}
