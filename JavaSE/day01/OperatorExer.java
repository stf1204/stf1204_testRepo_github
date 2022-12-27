class OperatorExer2 {
	
	public static void main(String[] args){
		int a0 = Integer.parseInt(args[0]);
		int a1 = Integer.parseInt(args[1]);
		int a2 = Integer.parseInt(args[2]);
		
		// 找出a1 和 a2 中的较大者
		int m = (a0 > a1)? a0 : a1;

		// 找出三者中的最大者
		m = (m > a2)? m : a2;
		System.out.println("max:" + m);
	}
}

public class OperatorExer{
	
	public static void main(String[] args){
		int a0 = 10;
		int a1 =30;
		int a2 = 70;
		// 找出a1 和 a2 中的较大者
		int m = (a0 > a1)? a0 : a1;

		// 找出三者中的最大者
		int max = (m > a2)? m : a2;
		System.out.println("max:" + max);
		
	}
}