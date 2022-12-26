
/*
编写程序：接收三个命令行字符串并转换为整数分别存入变量num1、num2、num3，
对它们进行排序(使用 if-else if-else),并且从小到大输出。尽量用嵌套.
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



// 1)对下列代码，若有输出，指出输出结果。

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
		if(b == false)  //如果写成if(b=false)能编译通过吗？如果能，结果是？
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
大家都知道，男大当婚，女大当嫁。那么女方家长要嫁女儿，当然要提出一定的条件：高：180cm以上；富：财富1千万以上；帅：是。
如果这三个条件同时满足，则：“我一定要嫁给他!!!”
如果三个条件有为真的情况，则：“嫁吧，比上不足，比下有余。”
如果三个条件都不满足，则：“不嫁！
*/

class HomeWork3{
	
	public static void main(String[] args){
		int height = Integer.parseInt(args[0]);
		int money = Integer.parseInt(args[1]);
		boolean is_cool = Integer.parseInt(args[2]) != 0; //比较运算的结果一定是Boolean
		
		if (height >= 180 && money >=1000 && is_cool){
			System.out.println("我一定要嫁给他!!!");
		} else if(height >= 180 || money >=1000 || is_cool){
			System.out.println("嫁吧，比上不足，比下有余.");
		}else{
			System.out.println("不嫁！");
			}
		}
	}
	
	
	
	/*
编写程序：接收三个命令行字符串并转换为整数分别存入变量num1、num2、num3，num4，num5
对它们进行排序(使用 冒泡),并且从小到大输出。
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
		} // n5搞定
		
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
		} // n4搞定
		
		if(n1 > n2){
			tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		if(n2 > n3){
			tmp = n2;
			n2 = n3;
			n3 = tmp;
		} // n3搞定
		
		if(n1 > n2){
			tmp = n1;
			n1 = n2;
			n2 = tmp;
		} // n2 搞定 同时n1也搞定
		
		System.out.println(n1 +","+ n2 +"," + n3 + ","+ n4 +"," + n5);
		
	
	}
}


/*
1.使用 switch 把小写类型的 char型转为大写。只转换 a, b, c, d, e. 其它的输出 “other”。
提示:接收命令行参数的字符方法
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

class HomeWork55{  // 改进 利用穿透
	
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


class HomeWork56{  // 再改进 利用穿透   
	
	public static void main(String[] args){
		char ch = args[0].charAt(0);
		String str = "other";    // 赋初始值，隐含逻辑
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
2.根据从命令行参数获取的月份，打印该月份所属的季节。
3,4,5 春季 6,7,8 夏季  9,10,11 秋季 12, 1, 2 冬季
*/

class HomeWork6{
	
	public static void main(String[] args){
		int month = Integer.parseInt(args[0]);
		switch (month){
			case 3:
			case 4:
			case 5:
				System.out.println("春季");
				break;
			case 6:
			case 7:
			case 8:
				System.out.println("夏季");
				break;
			case 9:
			case 10:
			case 11:
				System.out.println("秋季");
				break;
			case 1:
			case 2:
			case 12:
				System.out.println("冬季");
				break;
			default:
				System.out.println("输入季节有误，请重新输入");
				break;
		}	
	}
}

/*

接收命令行参数年、月、日，判断这一天是当年的第几天
 
   注：判断一年是否是闰年的标准：
       1）可以被4整除，但不可被100整除
       2）可以被400整除
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
			System.out.println("这一天是该年的第"+ days +"天");
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
		// 求1000以内能被13整除的数的平均值
		
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



class HomeWork121{  // 空心的等腰三角形
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
		// 输出100以内的所有质数
		
		for(int i=2;i<100;i++){
			boolean flag = true;
			for(int j=2;j< i;j++){
				if(i%j==0){
					flag = false;
				}
			}
			if(flag){
				System.out.println(i+"是质数");
			}
		}
	}
}



class HomeWork313{
	public static void main(String[] args){
		
		// 输出n以内的所有质数
		int n = Integer.parseInt(args[0]);
		
		for(int i=2;i<n;i++){
			boolean flag = true;
			for(int j=2;j< i;j++){
				if(i%j==0){
					flag = false;
				}
			}
			if(flag){
				System.out.println(i+"是质数");
			}
		}
	}
}



class HomeWork312{
	public static void main(String[] args){
		// 输出100-200以内的所有质数
		
		for(int i=100;i<200;i++){
			boolean flag = true; //i刚开始都假设是质数
			for(int j=2;j< i;j++){   // 循环求2~i 的余数
				if(i%j==0){   //余数为0
					flag = false;   // 有其他公因式，把标志位定为false
				}
			}
			if(flag){     // flag为true 说明是质数
				System.out.println(i+"是质数");
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
				System.out.println(i+"是质数");
			}
		}
	}
}


class HomeWork421{
	
	public static void main(String[] args){
		// 从命令行输入一个n 让一个本来循环100次的循环，实际执行n次
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
		
		// 打印100以内所有能被7整除的数 用continue做
		
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
			System.out.println(i+"是质数");
		}
	}
}