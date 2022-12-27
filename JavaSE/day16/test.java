import java.util.Date;
import java.text.SimpleDateFormat;


public class test{
	
	public static void main(String[] args) throws Exception{
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(true){
			//Date date = new Date();
			long time = System.currentTimeMillis();
			//String d2 = d.format(date);
			String d2 = d.format(time);
			//System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b"+d2);
			System.out.print("\r"+d2);

            Thread.sleep(1000);


		
	}
}
}