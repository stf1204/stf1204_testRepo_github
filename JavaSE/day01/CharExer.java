// 练习：打印输出你的名字的unicode码值，把你的姓加1000 打印输出
public class CharExer{
	public static void main(String[] args){
		char c1 = '孙';
		char c2 = '腾';
		char c3 = '飞';
		System.out.println((int)c1);
		System.out.println((int)c2);
		System.out.println((int)c3);
		char c4 = (char)(c1 + 1000);
		System.out.println(c4);
		
	}
}