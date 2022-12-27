package interfaceTest;

/*
代理模式：把代理对象当作是被代理对象来使用，代理对象和被代理对象达到同样标准

场景1、使用者无法直接创建被代理对象
场景2、统一对所有被代理对象的方法进行升级，无法直接修改被代理对象的类
 */
interface Housesale{
    void sale();
}
class fangdong1 implements Housesale{

    @Override
    public void sale() {
        System.out.println("f1我只要银行卡");
    }
}

class fangdong2 implements Housesale{

    @Override
    public void sale() {
        System.out.println("f2我只要现金");
    }
}

class lianjia implements Housesale{

    // private Housesale hr =new fangdong2();
    private Housesale hg =new fangdong1();

    @Override
    public void sale() {
        hg.sale();
        System.out.println("也可以支持支付宝");
        System.out.println("也可以支持微信");
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        Housesale h = new lianjia();
        h.sale();

    }
}
