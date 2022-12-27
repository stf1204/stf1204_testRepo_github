public class MethodTest{
	
	// 求两个数的和
	public static int add(int a, int b){
		System.out.println("add(int a,int b)...");
		int c = a+b;
		return c;
	}
	public static void main(String[] args){
		System.out.println("main begin");
		
		// 我是调用者
		int c = add(10,20);
		// System.out.println(c); 方法的局部变量不可以随意跨方法使用
		System.out.println(c);
		
		
		System.out.println("main end");
	}
	

// 无参无返回值类型
// 无返回值方法的调用不可以用变量接受返回值，也不可以打印方法调用本身，纯粹调用即可

public static void Test(){
	System.out.println("Test1()...");
	System.out.println("Test2()...");
	System.out.println("Test3()...");
	System.out.println("Test4()...");
	return;
}
}


class MethodTest2{
	public static void main(String[] args){
		
		
		System.out.println("main begin");
		method();
		System.out.println("~~~~~~~~~~~~~~~");
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		int x = method2(n,m);
		System.out.println("矩形的周长是"+x);
		System.out.println("~~~~~~~~~");
		System.out.println("main end");
		
		System.out.println("------------------------");
		int l = Integer.parseInt(args[0]);
		int c = method2(l);
		System.out.println("正方形的周长为："+c);
	}
	
	public static void method(){
		for(int i=0;i<20;i++){
			for(int j=0; j<8;j++){
				System.out.print("*");
			}
			System.out.println("");
		}
	}
	
	// 在方法中打印一个n*m的矩形，并在方法结束后返回矩形周长
	public static int method2(int n,int m){
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.print("*");
			}
			System.out.println("");
		}
		// 计算矩形周长并返回
		int a=(n+m)*2;
		return a;
	}
		
		//2. 	//在MethodTest2类中再声明一个重载方法method2，在方法中打印一个边长为n的正方形，并在方法结束后返回正方形周长, 在main方法中调用该方法, 并打印周长.
	public static int method2(int n){
		// 打印边长为n的正方形
		// 计算周长并返回.
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print("*");
			}
		System.out.println("");
		}
		
		int c = 4*n;
		return c;
	}

}

class MethodTest_1{
	
	public static void main(String[] args){
		//1000以内的所有质数的平均值.(应用break)
		int sum=0;
		int count=0;
		f1:for(int i=2;i<1000;i++){
			boolean flag = true;
			f2:for(int j=2;j<i;j++){
				if(i%j==0){
					flag = false;
					break;
				}
			}
			if (flag){
				System.out.println(i+"是质数");
				sum+=i;
				count++;
			}
		}
		
		int avg = sum/count;
		System.out.println("1000以内的质数的平均数avg="+avg);
		
	}
}


class MethodTest_2{
	
	public static void main(String[] args){
		//1000以内的所有质数的平均值.(应用continue)
		
		int sum=0;
		int count=0;
		f1:for(int i=2;i<1000;i++){
			f2:for(int j=2;j<i;j++){
				if(i%j==0){
					continue f1;
				}	
			}
			sum+=i;
			count++;
			System.out.println(i+"是质数");
		}
		int avg= sum/count;
		System.out.println("1000以内的质数的平均数avg="+avg);
		
	}
}


//3.定义三个重载方法max()，第一个方法求两个int值中的最大值，第二个方法求两个double值中的最大值，第三个方法求三个double值中的最大值，并分别调用三个方法。

class MethodTest0{
	public static void main(String[] args){
		
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		int max = Max(a,b);
		System.out.println(max);
		System.out.println("____________________________________");
		
		double c = 119.0;
		double d = 122.0;
		double e = 114.0;
		
		double max_3 = Max(c,d,e);
		System.out.println(max_3);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		double max_2 = Max(d,c);
		System.out.println(max_2);
	}
	
	public static short Max(short a,short b){
		short max = a>b ? a:b;
		return max;
	}
	public static double Max(double a, double b){
		double max = a > b ? a : b ;
		return max;
	}
	public static double Max(double a, double b,double c){
		double max = a>b ? a:b;
		max = max > c ? max:c;
		return max;
	}
	public static int Max(int a, int b){
		int max = a>b ? a:b;
		return max;
	}
}


class method{
	
		//20*8
		public static void method(){
			/*
			for(int i=0;i<20;i++){
				for(int j=0;j<8;j++){
					System.out.print("*");
				}
				System.out.println("");
			}
			*/
			//重载
			method(20,8);
		}
		
		// n*m
		public static int method(int a,int b){
			
			for(int i=0;i<a;i++){
				for(int j=0;j<b;j++){
					System.out.print("#");
				}
				System.out.println("");
			}
			int l = (a+b)*2;
			return l;
		}
		
		// n*n
		public static int method(int a){
			/*
			for(int i=0;i<a;i++){
				for(int j=0;j<a;j++){
					System.out.print("*");
				}
				System.out.println("");
			}
			int l = 4*a;
			return l;
			*/
			// 重载
			int l=method(a,a);
			return l;
			
		}
	public static void main(String[] args){
		method();
		int a= method(3,2);
		System.out.println("矩形的周长："+a);
		int b =method(2);
		System.out.println("正方形的周长："+b);
	}
}