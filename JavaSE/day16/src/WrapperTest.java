import org.junit.Test;

public class WrapperTest {

    @Test
    public void StringBuilderTest1() {
        String s1= "我很好";
        String s2= "你呢";
        String s3= "how are you";
        String s4= "what about you";

        StringBuilder s = new StringBuilder(s1);
        //"how what about youare you我很好你呢"
        //"hot about youare you我很好你呢"
        s.append(s2).insert(0,s3).insert(4,s4).delete(2,7);
        System.out.println(s);

    }

    @Test
    public void StringBuilderTest() {

        //默认长度为16
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder(222);
        StringBuilder sb = new StringBuilder("abc");
        System.out.println("sb.length() = " + sb.length());
        System.out.println("sb1.length() = " + sb1.length());
        System.out.println("sb2.length() = " + sb2.length());

        sb.append("我是嘿嘿嘿");
        sb.append(false);
        sb.append(123);
        System.out.println(sb);

        sb.insert(5,"学生");
        sb.insert(7,"true");
        System.out.println(sb);

        sb.delete(11,14);
        System.out.println(sb);

        sb.setCharAt(3,'你');
        System.out.println(sb);

    }


    @Test
    public void split(){
        String path = "D:\\anaconda3\\condabin;E:\\IDEA\\jdk1.8.0_241\\bin;C:\\Program Files\\NVIDIA GPU Computing Toolkit\\CUDA\\v11.1\\bin;C:\\Program Files\\NVIDIA GPU Computing Toolkit\\CUDA\\v11.1\\libnvvp;C:\\Program Files (x86)\\Intel\\Intel(R) Management Engine Components\\iCLS\\;C:\\Program Files\\Intel\\Intel(R) Management Engine Components\\iCLS\\;C:\\Windows\\system32;C:\\Windows;C:\\Windows\\System32\\Wbem;C:\\Windows\\System32\\WindowsPowerShell\\v1.0\\;C:\\Windows\\System32\\OpenSSH\\;C:\\Program Files (x86)\\Intel\\Intel(R) Management Engine Components\\DAL;C:\\Program Files\\Intel\\Intel(R) Management Engine Components\\DAL;C:\\Program Files (x86)\\Intel\\Intel(R) Management Engine Components\\IPT;C:\\Program Files\\Intel\\Intel(R) Management Engine Components\\IPT;E:\\IDEA\\PyCharm 2020.1\\Scripts;E:\\IDEA\\PyCharm 2020.1;E:\\IDEA\\hadoop-2.6.5;E:\\IDEA\\hadoop-3.3.0;E:\\IDEA\\hadoop-2.7.2;\\bin;E:\\IDEA\\apache-maven-3.6.0\\bin;E:\\IDEA\\apache-maven-3.6.0;E:\\linux iso\\mysql +hive\\win------mysql5.7\\mysql-5.7\\mysql\\bin;D:\\anaconda3\\envs\\pyspark\\python.exe;E:\\IDEA\\scala-2.11.12\\bin;C:\\Program Files\\Git\\cmd;D:\\anaconda3;D:\\anaconda3\\Lib;D:\\anaconda3\\Scripts;D:\\anaconda3\\Library\\bin;D:\\anaconda3\\envs\\pyspark\\Lib\\site-packages\\pydotplus;D:\\Xmanager\\Xmanager 7\\;D:\\Xmanager\\Xshell 7\\;D:\\Xmanager\\Xftp 7\\;D:\\Xmanager\\Xlpd 7\\;C:\\Program Files\\NVIDIA Corporation\\Nsight Compute 2020.2.1\\;C:\\Program Files (x86)\\NVIDIA Corporation\\PhysX\\Common;C:\\Program Files\\NVIDIA Corporation\\NVIDIA NvDLISR;\n";
        String[] split = path.split(";");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    public void exer4() {
        String str = "\t\n\tabcdefghijklmn\t\t\t\n";

        //replace() 替换
        String str1=str.replace('a','e');
        String str2=str.replace("\t","");
        String str3=str.replace("\n","");
        System.out.println("str1 = " + str1);
        System.out.println("str2 = " + str2);
        System.out.println("str3 = " + str3);

        // trim() 修建字符串，去除字符串首尾的空格，制表符，换行，不可见字符等
        String t1 = str1.trim();
        System.out.println("t1 = " + t1);

    }


    @Test
    public void exer3() {
        String str = "abcdefghijklmn";
        //c~i
        int beginindex = 2;
        int endindex = 9;

        char[] c1 = str.toCharArray();

        // 用i来控制交换次数
        for (int i = 0; i < (endindex - beginindex) / 2; i++) {
            char tmp = c1[beginindex];
            c1[beginindex] = c1[endindex - 1];
            c1[endindex - 1] = tmp;
            beginindex++;
            endindex--;
            String s = new String(c1);
            System.out.println("s = " + s);
        }

    }


    @Test
    public void exer2() {
        String str = "abcdefghijklmn";
        //c~i
        int beginindex = 2;
        int endindex = 9;
        //substring() 取子串
        String s1 = str.substring(0, beginindex);
        String s2 = str.substring(beginindex, endindex);
        String s3 = str.substring(endindex);
        System.out.println(s1 + "---" + s2 + "-----" + s3 + "---");

        char[] c1 = s2.toCharArray();
        for (int i = 0; i < c1.length / 2; i++) {
            char tmp = c1[i];
            c1[i] = c1[c1.length - 1 - i];
            c1[c1.length - 1 - i] = tmp;
        }
        String s4 = new String(c1);
        System.out.println(s1 + s4 + s3);

    }


    @Test
    public void exer1() {
        //indexOf()获取子串下标
        String s1 = "abkkcadkabkebfkabkskab";
        String s2 = "ab";
        int count = 0;
        int index = 0;
        while (true) {
            index = s1.indexOf(s2, index);
            if (index == -1) {
                break;
            } else {
                count++;
                index += 1;
            }
        }
        System.out.println("count = " + count);
    }

    @Test
    public void method1() {
        // 都在gc区 地址不同
        Integer i = new Integer(1);
        Integer j = new Integer(1);

        //false
        System.out.println(i == j);

        // -128~ 127 之间的数字 装箱时 都是存储在 cache缓冲区的同一个地址
        Integer m = 1;
        Integer n = 1;
        //true
        System.out.println(m == n);

        // 数据>127，或者＜-128 装箱时会new 一个新的地址
        Integer x = 128;
        Integer y = 128;

        // true
        System.out.println(x == y);
        System.out.println(x.equals(y));
    }


    //arraycopy

    @Test
    public void arraycopy() {
        String[] c1 = new String[1];
        c1[0] = "我 爱 你 ， 你呢？ 爱 不 爱我呢";
        // c1[1]="我 爱 你 ， 你呢？ 爱 不 爱我呢";

        String[] c2 = new String[1];

        System.arraycopy(c1, 0, c2, 0, 1);

        for (String s : c2) {
            System.out.print(s);
        }
        System.out.println();
        System.out.println(c1.length);
        System.out.println(c2.length);
    }

    @Test
    public void eett1() {
        String s1 = "我 爱 你 ， 你呢？ 爱 不 爱我呢";
        System.out.println(s1.length());
        String s2 = "";
        for (int i = 0; i < s1.length(); i++) {
            s2 += s1.charAt(s1.length() - 1 - i);
        }
        System.out.println(s2);
    }

    @Test
    public void eett2() {
        String s1 = "我 爱 你 ， 你呢？ 爱 不 爱我呢";
        System.out.println(s1.length());
        String s2 = "";

        for (int i = 0; i < s1.length(); i++) {
            s2 = s1.charAt(i) + s2;
        }
        System.out.println(s2);
    }

    @Test
    public void eett3() {
        String s1 = "我 爱 你 ， 你呢？ 爱 不 爱我呢";
        System.out.println(s1.length());

        char[] chars = s1.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char tmp = chars[0];
            chars[0] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = tmp;
        }
        String s3 = new String(chars);
        System.out.println(s3);
    }

    @Test
    public void eett4() {
        String s1 = "我 爱 你 ， 你呢？ 爱 不 爱我呢";
        System.out.println(s1.length());

        for (int i = 0; i < s1.length(); i++) {
            System.out.print(s1.charAt(i));
        }
        System.out.println();
        System.out.println("我是分隔符-------------------------------");

        String s2 = "";
        for (int i = s1.length() - 1; i >= 0; i--) {
            char c1 = s1.charAt(i);
            s2 += c1;
        }
        System.out.println(s2);
        System.out.println("我是分隔符-------------------------------");

        for (int i = 0; i < s2.length(); i++) {
            System.out.print(s2.charAt(i));
        }
        System.out.println();
        System.out.println(s2.length());
    }


    @Test
    public void test1() {
        String s1 = "111";
        String s2 = "222";

        // 先转int，再装箱
        int i = Integer.parseInt(s1);
        Integer i1 = new Integer(i);

        //直接装箱(三种方法)
        Integer i2 = new Integer(Integer.decode(s2));
        // Integer i2 = new Integer(i);
        // Integer i2 = new Integer(Integer.valueOf(s2));

        //手工拆箱求和
        int i3 = i1.intValue();
        int i4 = i2.intValue();
        System.out.println("i3+i4 = " + (i3 + i4));

        //自动拆箱求积
        int i5 = i1;
        int i6 = i2;
        System.out.println("15*i6 = " + (i5 * i6));


    }
}
