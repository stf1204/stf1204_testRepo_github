package homework;

public class Keyboard  extends InputDevice{
    private int keycount;

    public int getKeycount() {
        return keycount;
    }

    public void setKeycount(int keycount) {
        this.keycount = keycount;
    }

    public void type(){
        System.out.println("键盘输入中。。。。");
    }

    public Keyboard() {
    }

    public Keyboard(String name, double price, String factory,int keycount) {
        super(name, price, factory);
        this.keycount=keycount;
    }

    @Override
    public String say(){
        return super.say()+",键数:"+keycount;
    }
}
