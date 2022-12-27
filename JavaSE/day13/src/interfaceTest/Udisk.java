package interfaceTest;

public class Udisk implements USB {
    public  void connect(){
        System.out.println("connect");
    }

    public  void work(){
        System.out.println("work");
    }

    public  void disconnect(){
        System.out.println("disconnect");
    }
}
