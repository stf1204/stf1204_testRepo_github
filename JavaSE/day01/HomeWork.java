
/*
��д���򣺽��������������ַ�����ת��Ϊ�����ֱ�������num1��num2��num3��
�����ǽ�������(ʹ�� if-else if-else),���Ҵ�С���������������Ƕ��.
*/

public class HomeWork{
	
	public static void main(String[] args){
		int i1 = Integer.parseInt(args[0]);
		int i2 = Integer.parseInt(args[1]);
		int i3 = Integer.parseInt(args[2]);
		if(i1 < i2){ // 1<2
			if(i2< i3){ 			// 1<2 2<3
				System.out.println(i1+","+i2+","+i3);
			}else if(i1 < i3){     // 1<2  3<2  1<3
				System.out.println(i1+","+i3+","+i2);
			}else{ // 1<2 3<2 3<1
				System.out.println(i3+","+i1+","+i2);
			}
		}else{ // 2<1
			if(i3 < i2) {  // 2<1 3<2
				System.out.println(i3+","+ i2+","+i1);
			}else if (i1 < i3){ // 2<1 2<3 1<3
				System.out.println(i2+","+i1 +","+i3);
			}else{ // 2<1 2<3 3<1
				System.out.println(i2+","+i3 +","+i1);
			}	
			}
		}
	}



// 1)�����д��룬���������ָ����������

class HomeWork2{
	
	public static void main(String[] args){	
		int x = 4;
		int y = 1;
		if (x > 2) {
			if (y > 2) 
				System.out.println(x + y); 
				System.out.println("atguigu");
		} else
			System.out.println("x is " + x);
		   
		   // ---> atguigu
		   
// 2)
		boolean b = true;
		if(b == false)  //���д��if(b=false)�ܱ���ͨ��������ܣ�����ǣ�
			System.out.println("a");
		else if(b)
			System.out.println("b");
		else if(!b)
			System.out.println("c");
		else
			System.out.println("d");  
		// --->b
	}
}




/*
��Ҷ�֪�����д󵱻飬Ů�󵱼ޡ���ôŮ���ҳ�Ҫ��Ů������ȻҪ���һ�����������ߣ�180cm���ϣ������Ƹ�1ǧ�����ϣ�˧���ǡ�
�������������ͬʱ���㣬�򣺡���һ��Ҫ�޸���!!!��
�������������Ϊ���������򣺡��ްɣ����ϲ��㣬�������ࡣ��
������������������㣬�򣺡����ޣ�
*/

class HomeWork3{
	
	public static void main(String[] args){
		int height = Integer.parseInt(args[0]);
		int money = Integer.parseInt(args[1]);
		boolean is_cool = Integer.parseInt(args[2]) != 0; //�Ƚ�����Ľ��һ����Boolean
		
		if (height >= 180 && money >=1000 && is_cool){
			System.out.println("��һ��Ҫ�޸���!!!");
		} else if(height >= 180 || money >=1000 || is_cool){
			System.out.println("�ްɣ����ϲ��㣬��������.");
		}else{
			System.out.println("���ޣ�");
			}
		}
	}
	
	
	
	/*
��д���򣺽��������������ַ�����ת��Ϊ�����ֱ�������num1��num2��num3��num4��num5
�����ǽ�������(ʹ�� ð��),���Ҵ�С���������
*/

class HomeWork4{
	public static void main(String[] args){
		int n1 = Integer.parseInt(args[0]);
		int n2 = Integer.parseInt(args[1]);
		int n3 = Integer.parseInt(args[2]);
		int n4 = Integer.parseInt(args[3]);
		int n5 = Integer.parseInt(args[4]);
		int tmp;
		
		if(n1 > n2){
			tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		if(n2 > n3){
			tmp = n2;
			n2 = n3;
			n3 = tmp;
		}
		if(n3 > n4){
			tmp = n3;
			n3 = n4;
			n4 = tmp;
		}
		if (n4 > n5){
			tmp = n4;
			n4 = n5;
			n5 = tmp;
		} // n5�㶨
		
		if(n1 > n2){
			tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		if(n2 > n3){
			tmp = n2;
			n2 = n3;
			n3 = tmp;
		}
		if(n3 > n4){
			tmp = n3;
			n3 = n4;
			n4 = tmp;
		} // n4�㶨
		
		if(n1 > n2){
			tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		if(n2 > n3){
			tmp = n2;
			n2 = n3;
			n3 = tmp;
		} // n3�㶨
		
		if(n1 > n2){
			tmp = n1;
			n1 = n2;
			n2 = tmp;
		} // n2 �㶨 ͬʱn1Ҳ�㶨
		
		System.out.println(n1 +","+ n2 +"," + n3 + ","+ n4 +"," + n5);
		
	
	}
}


/*
1.ʹ�� switch ��Сд���͵� char��תΪ��д��ֻת�� a, b, c, d, e. ��������� ��other����
��ʾ:���������в������ַ�����
char ch = args[0].charAt(0);
*/

class HomeWork5{
	
	public static void main(String[] args){
		char ch = args[0].charAt(0);
		switch (ch){
			case 'a':
				System.out.println("A");
				break;
			case 'b':
				System.out.println("B");
				break;
			case 'c':
				System.out.println("C");
				break;
			case 'd':
				System.out.println("D");
				break;
			case 'e':
				System.out.println("E");
				break;
			default:
				System.out.println("other");
		}		
	}
}

class HomeWork55{  // �Ľ� ���ô�͸
	
	public static void main(String[] args){
		char ch = args[0].charAt(0);
		switch (ch){
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			char c2 = (char)(ch-32);
				System.out.println(c2);
				break;
			default:
				System.out.println("other");
				break;
		}		
	}
}


class HomeWork56{  // �ٸĽ� ���ô�͸   
	
	public static void main(String[] args){
		char ch = args[0].charAt(0);
		String str = "other";    // ����ʼֵ�������߼�
		switch (ch){
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			str = ""+(char)(ch-32);
		}	
		System.out.println(str);
	}
}

/*
2.���ݴ������в�����ȡ���·ݣ���ӡ���·������ļ��ڡ�
3,4,5 ���� 6,7,8 �ļ�  9,10,11 �＾ 12, 1, 2 ����
*/

class HomeWork6{
	
	public static void main(String[] args){
		int month = Integer.parseInt(args[0]);
		switch (month){
			case 3:
			case 4:
			case 5:
				System.out.println("����");
				break;
			case 6:
			case 7:
			case 8:
				System.out.println("�ļ�");
				break;
			case 9:
			case 10:
			case 11:
				System.out.println("�＾");
				break;
			case 1:
			case 2:
			case 12:
				System.out.println("����");
				break;
			default:
				System.out.println("���뼾����������������");
				break;
		}	
	}
}

/*

���������в����ꡢ�¡��գ��ж���һ���ǵ���ĵڼ���
 
   ע���ж�һ���Ƿ�������ı�׼��
       1�����Ա�4�����������ɱ�100����
       2�����Ա�400����
*/

class HomeWork22{
	
	public static void main(String[] args){
		int year = Integer.parseInt(args[0]);
		int month = Integer.parseInt(args[1]);
		int day = Integer.parseInt(args[2]);
		
		int days= day;
		switch(month){
			case 12:{
				days+=30;
			}
			case 11:{
				days+=31;
			}
			case 10:{
				days+=30;
			}
			case 9:{
				days+=31;
			}
			case 8:{
				days+=31;
			}
			case 7:{
				days+=30;
			}
			case 6:{
				days+=31;
			}
			case 5:{
				days+=30;
			}
			case 4:{
				days+=31;
			}
			case 3:{
				days+=28;
				if((year %4 == 0 && year % 100 !=0) || year % 400 == 0){
					days++;
				}
			}
			case 2:{
				days+=31;
			}
			System.out.println("��һ���Ǹ���ĵ�"+ days +"��");
		}
	}
}



class HomeWork7{
	
	public static void main(String[] args){
		int i = 0;
		while(i<20){
			System.out.println("             ********");
			i++;
		}
	}
}

class HomeWork8{
	
	public static void main(String[] args){
		
		int n = Integer.parseInt(args[0]);
		int i = 0;
		while(i<n){
			System.out.println("             ********");
			i++;
		}
	}
}

class HomeWork9{
	
	public static void main(String[] args){
		// ��1000�����ܱ�13����������ƽ��ֵ
		
		int sum=0;
		int count = 0;
		for(int i=0;i<1000;i++){
			if (i % 13 == 0){
				sum += i;
				count++;
			}
		}
		System.out.println("sum:"+ sum);
		System.out.println("count:"+ count);
		System.out.println("avg"+ sum/count);

		
}
}

class HomeWork10{
	/*
	
		*  *  *  *
		*  *  *  *
		*  *  *  *
	*/
	public static void main(String[] args){
		int m=Integer.parseInt(args[0]);
		int n=Integer.parseInt(args[1]);
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				System.out.print(" * ");
			}
			
		System.out.println();
		}
	}
}



class HomeWork11{
	/*
	
	 *
    ***
   *****
  *******
 *********
	*/
	public static void main(String[] args){
		for(int i=0;i<5 ;i++){
			for(int j=0; j<4 -i;j++){
				System.out.print(" ");
			}
			for(int p =0;p<i*2+1;p++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
}



class HomeWork12{
	/*
	                *
                   ***
                  *****
                 *******
                *********
               ***********
              *************
             ***************
            *****************
           *******************
          *********************
         ***********************
	*/
	public static void main(String[] args){
		
		int m = Integer.parseInt(args[0]);
		for(int i=0;i<m ;i++){
			for(int j=0; j<m-1-i;j++){
				System.out.print(" ");
			}
			for(int p =0;p<i*2+1;p++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
}



class HomeWork121{  // ���ĵĵ���������
	/*
	
	
	*
   * *
  *   *
 *     *
*********
	
	*/
	public static void main(String[] args){
		
		int m = Integer.parseInt(args[0]);
		for(int i=0;i<m ;i++){
			for(int j=0; j<m-1-i;j++){
				System.out.print(" ");
			}
			for(int p =0;p<i*2+1;p++){
				if(i ==0|| i== m-1 || p==0 || p==i*2){
					System.out.print("*");
				}else{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}



class HomeWork31{
	public static void main(String[] args){
		// ���100���ڵ���������
		
		for(int i=2;i<100;i++){
			boolean flag = true;
			for(int j=2;j< i;j++){
				if(i%j==0){
					flag = false;
				}
			}
			if(flag){
				System.out.println(i+"������");
			}
		}
	}
}



class HomeWork313{
	public static void main(String[] args){
		
		// ���n���ڵ���������
		int n = Integer.parseInt(args[0]);
		
		for(int i=2;i<n;i++){
			boolean flag = true;
			for(int j=2;j< i;j++){
				if(i%j==0){
					flag = false;
				}
			}
			if(flag){
				System.out.println(i+"������");
			}
		}
	}
}



class HomeWork312{
	public static void main(String[] args){
		// ���100-200���ڵ���������
		
		for(int i=100;i<200;i++){
			boolean flag = true; //i�տ�ʼ������������
			for(int j=2;j< i;j++){   // ѭ����2~i ������
				if(i%j==0){   //����Ϊ0
					flag = false;   // ����������ʽ���ѱ�־λ��Ϊfalse
				}
			}
			if(flag){     // flagΪtrue ˵��������
				System.out.println(i+"������");
			}
		}
	}
}


class HomeWork32{
	
	public static void main(String[] args){
		
		int n=Integer.parseInt(args[0]);
		for(int i=2;i<n;i++){
			boolean flag = true;
			for(int j=2;j<i;j++){
				if(i%j==0){
					flag = false;
					break;
				}
			}
			if(flag){
				System.out.println(i+"������");
			}
		}
	}
}


class HomeWork421{
	
	public static void main(String[] args){
		// ������������һ��n ��һ������ѭ��100�ε�ѭ����ʵ��ִ��n��
		int n = Integer.parseInt(args[0]);
		int count = n>100 ? n: 100;
		for(int i =0;i<count;i++){
			System.out.println("i:"+i);
			if(i==n-1){
				break;
			}
		}
		System.out.println("after for ...");
	}
}
class HomeWork_1{
	
	public static void main(String[] args){
		
		// ��ӡ100���������ܱ�7�������� ��continue��
		
		for(int i=0;i<100;i++){
			if(i%7!=0){
				continue;
			}
			System.out.println("i:"+i);
		}
	}
}

class HomeWork_2{
	
	public static void main(String[] args){
		f1:for(int i =2; i<100; i++){
			f2:for(int j =2;j<i;j++){
				if(i%j==0){
					continue f1;
				}
			}
			System.out.println(i+"������");
		}
	}
}