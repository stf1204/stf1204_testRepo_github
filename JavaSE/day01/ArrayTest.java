class ArrayTest{
	
	// ����һ������ �����������ݺͽ�������ڣ�����ӡ
	public static void main(String[] args){
		int[] arr = new int[6];
		arr[0]=1998;
		arr[1]=12;
		arr[2]=4;
		arr[3]=2022;
		arr[4]=9;
		arr[5]=26;
		System.out.println("���գ�"+arr[0]+"��"+arr[1]+"��"+arr[2]+"��");
		System.out.println("��ǰʱ�䣺"+arr[3]+"��"+arr[4]+"��"+arr[5]+"��");
	}
}

class test{
	// ����������һ������Ϊ26��char���飬����26�� Сд��ĸ
	public static void main(String[] args){
		char[] arr = new char[26];
		for(int i=0;i<arr.length;i++){
			arr[i] = (char)('a'+i);
		}
		// ����		
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}

class test2{
	// ����������һ������26��char���飬����26�� ��д��ĸ
	public static void main(String[] args){
		char[] arr = new char[26];
		for(int i=0;i<arr.length;i++){
			arr[i]=(char)('A'+i);
		}
		// ����
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}

class test3{
	// ����������һ������36��char���飬����26�� ��д��ĸ��10�������ַ�
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
		
		// ����
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
}

class test4{
	// �Ľ� ����ֵ�ŵ�ͬһ��forѭ����
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
	
	// �Ľ����������жϴ洢����
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
		
		//��ȡ[0,1)���double
		double ran1 = Math.random();
		System.out.println(ran1);

		//��ȡ[0,100)���double		
		double ran2 = Math.random()*100;
		System.out.println(ran2);
		
		//��ȡ[0,1)���int
		int ran3 = (int)(Math.random()*100);
		System.out.println(ran3);
		
		//��ȡ[20,50)���int
		int ran4 = (int)(Math.random()*30+20);
		System.out.println(ran4);
	
	}
}

class Random2{
	
	public static void main(String[] args){
		int[] arr = new int[9];
		
		// ��ȡ�������
		for(int i=0;i<arr.length;i++){
			arr[i] = (int)(Math.random() * 20 + 30);
		}
		
		// ����
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
		
		// ��ƽ��ֵ
		int sum=0;
		for(int i=0;i<arr.length;i++){
			sum+=arr[i];
		}
		int avg = sum/arr.length;
		System.out.println("avg="+avg);
		
	}
}


/*
�õݹ�ķ�ʽ�� 1+2+3.... + 10.
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
�����������, ���ܱ�13����������ƽ��ֵ.
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
			System.out.println("�ܱ�13����������ƽ��ֵΪ0");
		}else{
			int avg = sum/count;
			System.out.println("�ܱ�13����������ƽ��ֵΪ:"+avg);
		}
	}
}