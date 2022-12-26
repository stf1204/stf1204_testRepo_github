// 1 写一个Variable类 main方法中使用double类型声明var1和var2变量，然后用var2保存var1与var2的商
// 2 声明字符串变量info 用info 串接上述计算过程并打印输出结果。
// 3 不要再声明var3 打印的结果要小学生也能看懂，也就是说不要出现变量名，只要表达式即可
public class Variable{
	
	public static void main(String[] args){
		double var1 = 38;
		double var2 = 5;
		
		String info = var1 + " / " + var2 + " = "; //在var2改变之前就把他的老数据抓住
		
		var2 = var1 / var2;
		
		// "38 / 5 = 7.6"
		
		info = info + var2;
		// String str = var1 + " / " + (var1 / var2) + " = " var2; //有些情况下数据还原可能出现问题
		
		System.out.println(info); 
	}
}