package homework;

public class test {
/*
Device, 表示设备, 属性String name, double price, String factory, 有say(). 属性要封装 提供2个构造器
写子类InputDevice, 没有属性和方法

写子类Mouse是InputDevice的子类, 特有属性 : String color, 特有方法, public void move(), 重写say
写子类Keyboard是 InputDevice的子类, 特有属性 : int keyCount, 特有方法 public void type()

在测试类中创建2个鼠标对象, 2个键盘, 1个输入设备, 并保存在一个数组中  打印输出对象的详细信息
使用 选择排序 对所有设备按照价格降序排序.
*/
public static void main(String[] args) {

    Device mouse = new Mouse("罗技",200,"华为","红");
    Device mouse1 = new Mouse("罗技",300,"华为","蓝");
    Device k1 = new Keyboard("未知",1200,"华为",2200);
    Device k12 = new Keyboard("雷蛇",2200,"华为",3000);
    Device in = new InputDevice("未知", 10, "三星");

    // 多态数组
    Device[] d= new Device[5];
    d[0]=mouse;
    d[1]=mouse1;
    d[2]=k1;
    d[3]=k12;
    d[4]=in;

    for (Device device : d) {
        System.out.println(device.say());
    }

    System.out.println("**********************************");
    //快速排序
    for (int i = 0; i < d.length-1; i++) {
        int index=i;
        for (int j = i+1; j < d.length; j++) {
            if (d[j].getPrice()<d[index].getPrice()){
                index=j;
            }
        }
        if(index!=i){
            Device tmp = d[index];
            d[index]=d[i];
            d[i]=tmp;
        }
    }

    for (Device device : d) {
        System.out.println(device.say());
    }

}
}
