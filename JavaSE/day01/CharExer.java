// ��ϰ����ӡ���������ֵ�unicode��ֵ��������ռ�1000 ��ӡ���
public class CharExer{
	public static void main(String[] args){
		char c1 = '��';
		char c2 = '��';
		char c3 = '��';
		System.out.println((int)c1);
		System.out.println((int)c2);
		System.out.println((int)c3);
		char c4 = (char)(c1 + 1000);
		System.out.println(c4);
		
	}
}