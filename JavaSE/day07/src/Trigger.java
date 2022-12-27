public class Trigger {
    //4.编写两个类，Triangle和TriangleTest，其中Triangle中声明私有的底边长base和高height，
    // 同时声明公共方法访问私有变量；另一个类中使用这些公共方法，计算三角形的面积。
    private int base;
    private int height;

    public Trigger(){
        this(10,10);
    }
    public Trigger(int base,int height){
        this.base=base;
        this.height=height;
    }

    public void setBase(int base){
        this.base=base;
    }
    public void setHeight(int height){
        this.height=height;
    }
    public int getBase(){
        return base;
    }
    public int getHeight(){
        return height;
    }
    public void say(){
        System.out.println("base:"+base+"height"+height);
    }
}
