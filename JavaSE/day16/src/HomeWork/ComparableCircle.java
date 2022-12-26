package HomeWork;
/*
定义一个接口用来实现两个对象的比较。
interface CompareObject {
	public int compareTo(Object o);
}

定义一个Circle类。
定义一个ComparableCircle类，继承Circle类并且实现CompareObject接口。
在ComparableCircle类中给出接口中方法compareTo的实现体，用来比较两个圆的半径大小。
定义一个测试类TestInterface，创建两个ComparableCircle对象，调用compareTo方法比较两个类的半径大小。
 */

// 接口
interface CompareObject {
    int compareTo(Object o);
}

// 类
public class ComparableCircle extends Circle implements CompareObject {

    public ComparableCircle() {}
    public ComparableCircle(double r) {
        super(r);
    }


    // 比较两个圆的半径大小。
    //若返回值是 0 , 代表相等; 若为正数，代表当前对象大；负数代表当前对象小
    @Override
    public int compareTo(Object o) {
        if(o instanceof Circle) {
            Circle o1 = (Circle) o;  // 直接造型
            if (o1.getR() < this.getR()) {
                return 1;
            } else if (o1.getR() == this.getR()) {
                return 0;
            } else {
                return -1;
            }
        }else return 0x7fffffff;  //此处应该抛异常
    }
}

