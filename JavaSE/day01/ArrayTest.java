class ArrayTest{
	
	// 创建一个数组 保存生日数据和今天的日期，并打印
	public static void main(String[] args){
		int[] arr = new int[6];
		arr[0]=1998;
		arr[1]=12;
		arr[2]=4;
		arr[3]=2022;
		arr[4]=9;
		arr[5]=26;
		System.out.println("生日："+arr[0]+"年"+arr[1]+"月"+arr[2]+"日");
		System.out.println("当前时间："+arr[3]+"年"+arr[4]+"月"+arr[5]+"日");
	}
}

class test{
	// 声明并创建一个长度为26的char数组，保存26个 小写字母
	public static void main(String[] args){
		char[] arr = new char[26];
		for(int i=0;i<arr.length;i++){
			arr[i] = (char)('a'+i);
		}
		// 遍历		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}

class test2{
	// 声明并创建一个长度26的char数组，保存26个 大写字母
	public static void main(String[] args){
		char[] arr = new char[26];
		for(int i=0;i<arr.length;i++){
			arr[i]=(char)('A'+i);
		}
		// 遍历
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}

class test3{
	// 声明并创建一个长度36的char数组，保存26个 大写字母和10个数字字符
	public static void main(String[] args){
		char[] arr = new char[36];
		//A-Z
		for(int i=0;i<26;i++){
			arr[i]=(char)('A'+i);
		}
		// 0-9
		for(int i=26;i<36;i++){
			arr[i]=(char)('0'+i-26);
		}
		
		// 遍历
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}

class test4{
	// 改进 将赋值放到同一个for循环中
	public static void main(String[] args){
		char[] arr = new char[36];
		for(int i=0;i<arr.length;i++){
			if(i<26){
				arr[i]=(char)('A'+i);
			}else{
				arr[i]=(char)('0'+i-26);
			}
		}
		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}


class test5{
	
	// 改进，避免了判断存储内容
	public static void main(String[] args){
		char[] arr = new char[36];
		char a='A';
		char b='0';
		for(int i=0;i<arr.length;i++){
			if(i<26){
				arr[i]=a++;
			}else{
				arr[i]=b++;
			}
		}
		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}


class code{
	public static void main(String[] args){
		int[] arr = new int[17];
		for(int i=0;i<arr.length;i++){
			arr[i]=2*i+1;
		}
		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
		
		int sum =0;
		for(int i=0;i<arr.length;i++){
			sum+=arr[i];
		}
		int avg = sum/arr.length;
		System.out.println("sum="+sum);
		System.out.println("avg="+avg);
	}
}


class Random{
	public static void main(String[] args){
		
		//获取[0,1)随机double
		double ran1 = Math.random();
		System.out.println(ran1);

		//获取[0,100)随机double		
		double ran2 = Math.random()*100;
		System.out.println(ran2);
		
		//获取[0,1)随机int
		int ran3 = (int)(Math.random()*100);
		System.out.println(ran3);
		
		//获取[20,50)随机int
		int ran4 = (int)(Math.random()*30+20);
		System.out.println(ran4);
	
	}
}

class Random2{
	
	public static void main(String[] args){
		int[] arr = new int[9];
		
		// 获取随机数组
		for(int i=0;i<arr.length;i++){
			arr[i] = (int)(Math.random() * 20 + 30);
		}
		
		// 遍历
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
		
		// 求平均值
		int sum=0;
		for(int i=0;i<arr.length;i++){
			sum+=arr[i];
		}
		int avg = sum/arr.length;
		System.out.println("avg="+avg);
		
	}
}


/*
用递归的方式求 1+2+3.... + 10.
*/
class testt{
		
	public static int test2t(int n){
		if(n==1){
			return 1;
		}
		return n+test2t(n-1);
	}

	public static void main(String[] args){
		System.out.println("-----------------");
		int re=test2t(10);
		System.out.println(re);
	}
}


/*
产生随机数列, 求能被13整除的数的平均值.
*/

class Randoms{
	
	public static void main(String[] args){
		
		int[] arr = new int[19];
		for(int i=0;i<arr.length;i++){
			arr[i]=(int)(Math.random()*30);
		}
		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
		
		int sum=0;
		int count=0;
		for(int i=0;i<arr.length;i++){
			if(arr[i]%13==0){
				sum+=arr[i];
				count++;
			}
		}
		if(count==0){
			System.out.println("能被13整除的数的平均值为0");
		}else{
			int avg = sum/count;
			System.out.println("能被13整除的数的平均值为:"+avg);
		}
	}
}