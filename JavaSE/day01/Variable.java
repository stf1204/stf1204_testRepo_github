// 1 дһ��Variable�� main������ʹ��double��������var1��var2������Ȼ����var2����var1��var2����
// 2 �����ַ�������info ��info ��������������̲���ӡ��������
// 3 ��Ҫ������var3 ��ӡ�Ľ��ҪСѧ��Ҳ�ܿ�����Ҳ����˵��Ҫ���ֱ�������ֻҪ���ʽ����
public class Variable{
	
	public static void main(String[] args){
		double var1 = 38;
		double var2 = 5;
		
		String info = var1 + " / " + var2 + " = "; //��var2�ı�֮ǰ�Ͱ�����������ץס
		
		var2 = var1 / var2;
		
		// "38 / 5 = 7.6"
		
		info = info + var2;
		// String str = var1 + " / " + (var1 / var2) + " = " var2; //��Щ��������ݻ�ԭ���ܳ�������
		
		System.out.println(info); 
	}
}