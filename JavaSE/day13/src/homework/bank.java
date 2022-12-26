package homework;

public class bank {
    /*
编写一个类实现银行账户的概念，包含的属性有“帐号”、“密码”、“存款余额”、“利率”、“最小余额”，
定义封装这些属性的方法。账号要自动生成。
编写主类，使用银行账户类，输入、输出3个储户的上述信息。

考虑：哪些属性可以设计成static属性。问一个问题? 这个数据能共享吗?
     */

    //静态变量 类变量
    private static double bite=3.2;
    private static double last_num=300;
    private static long counter =6230000728384938224L;

    //非静态变量 对象变量
    private long account;
    private String password;
    private int account_num;

    public bank() {
    }

    public bank(String password, int account_num) {
        this.account = counter++;
        this.password = password;
        this.account_num = account_num;
    }

    ///////////////////////类方法////////////////////////////////////////
    public static double getBite() {
        return bite;
    }

    public static double getLast_num() {
        return last_num;
    }

    public static void setBite(double bite){
        bank.bite =bite;
    }

    public  static void setLast_num(double last_num){
        bank.last_num=last_num;
    }

    ///////////////////////对象方法////////////////////////////////////////

    public long getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public int getAccount_num() {
        return account_num;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount_num(int account_num) {
        this.account_num = account_num;
    }


    @Override
    public String toString() { // toString不要静态属性 只与对象有关的东西
        return "bank{" +
                "账户=" + account +
                ", 密码='" + password + '\'' +
                ", 余额=" + account_num +
                '}';
    }
}
