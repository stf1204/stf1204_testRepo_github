public class MethodTest{
	
	// ���������ĺ�
	public static int add(int a, int b){
		System.out.println("add(int a,int b)...");
		int c = a+b;
		return c;
	}
	public static void main(String[] args){
		System.out.println("main begin");
		
		// ���ǵ�����
		int c = add(10,20);
		// System.out.println(c); �����ľֲ���������������緽��ʹ��
		System.out.println(c);
		
		
		System.out.println("main end");
	}
	

// �޲��޷���ֵ����
// �޷���ֵ�����ĵ��ò������ñ������ܷ���ֵ��Ҳ�����Դ�ӡ�������ñ���������ü���

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
		System.out.println("���ε��ܳ���"+x);
		System.out.println("~~~~~~~~~");
		System.out.println("main end");
		
		System.out.println("------------------------");
		int l = Integer.parseInt(args[0]);
		int c = method2(l);
		System.out.println("�����ε��ܳ�Ϊ��"+c);
	}
	
	public static void method(){
		for(int i=0;i<20;i++){
			for(int j=0; j<8;j++){
				System.out.print("*");
			}
			System.out.println("");
		}
	}
	
	// �ڷ����д�ӡһ��n*m�ľ��Σ����ڷ��������󷵻ؾ����ܳ�
	public static int method2(int n,int m){
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				System.out.print("*");
			}
			System.out.println("");
		}
		// ��������ܳ�������
		int a=(n+m)*2;
		return a;
	}
		
		//2. 	//��MethodTest2����������һ�����ط���method2���ڷ����д�ӡһ���߳�Ϊn�������Σ����ڷ��������󷵻��������ܳ�, ��main�����е��ø÷���, ����ӡ�ܳ�.
	public static int method2(int n){
		// ��ӡ�߳�Ϊn��������
		// �����ܳ�������.
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
		//1000���ڵ�����������ƽ��ֵ.(Ӧ��break)
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
				System.out.println(i+"������");
				sum+=i;
				count++;
			}
		}
		
		int avg = sum/count;
		System.out.println("1000���ڵ�������ƽ����avg="+avg);
		
	}
}


class MethodTest_2{
	
	public static void main(String[] args){
		//1000���ڵ�����������ƽ��ֵ.(Ӧ��continue)
		
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
			System.out.println(i+"������");
		}
		int avg= sum/count;
		System.out.println("1000���ڵ�������ƽ����avg="+avg);
		
	}
}


//3.�����������ط���max()����һ������������intֵ�е����ֵ���ڶ�������������doubleֵ�е����ֵ������������������doubleֵ�е����ֵ�����ֱ��������������

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
			//����
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
			// ����
			int l=method(a,a);
			return l;
			
		}
	public static void main(String[] args){
		method();
		int a= method(3,2);
		System.out.println("���ε��ܳ���"+a);
		int b =method(2);
		System.out.println("�����ε��ܳ���"+b);
	}
}