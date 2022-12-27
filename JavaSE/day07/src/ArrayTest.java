public class ArrayTest {

    public static void main(String[] args) {

    }


    public static void mao_pao(String[] args) {
        int[] arr = new int[8];

        //冒泡
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        //bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //mao
        for (int i = 0; i < arr.length - 1; i++) {  //外循环负责趟数
            for (int j = 0; j < arr.length - 1 - i; j++) { //内循环负责比较次数
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        // bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void if_max_min_index(String[] args) {
        int[] arr = new int[18];

        //chu shi hua
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 30);
        }

        // bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i] + " ");
        }
        System.out.println();

        //max index
        int Maxindex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {  //能被7整除
                if (Maxindex == -1) {
                    Maxindex = i;// 第一个能被整除
                } else {//后面其他的能被整除的数
                    if (arr[Maxindex] < arr[i]) {
                        Maxindex = i;
                    }
                }
            }
        }
        if (Maxindex == -1) {
            System.out.println("没有能被7整除的数");
        } else {
            System.out.println("Maxindex = " + Maxindex);
            System.out.println("最大数为 = " + arr[Maxindex]);
        }

        // min index
        int Minindex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {
                if (Minindex == -1) {
                    Minindex = i;
                } else {
                    if (arr[Minindex] > arr[i]) {
                        Minindex = i;
                    }
                }
            }

        }
        if (Minindex == -1) {
            System.out.println("没有被7整除的数");
        } else {
            System.out.println("Minindex = " + Minindex);
            System.out.println("最小数为 = " + arr[Minindex]);
        }
    }

    public static void reverse(String[] args) {
        int[] arr = new int[9];

        // fu zhi
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        // bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();


        //反转
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp;
            tmp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;
        }

        //bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void max_min_index(String[] args) {
        int[] arr = new int[20];
        //fu zhi
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }

        // bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //maxindex
        int Maxindex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[Maxindex]) {
                Maxindex = i;
            }
        }
        System.out.println("Maxindex = " + Maxindex);
        System.out.println("Max = " + arr[Maxindex]);


        //minindex
        int Minindex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[Minindex]) {
                Minindex = i;
            }
        }
        System.out.println("Minindex = " + Minindex);
        System.out.println("Min = " + arr[Minindex]);
    }

    public static void if_max_min(String[] args) {
        int[] arr = new int[100];

        //fu zhi
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //max
        int max = 0x80000000;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 13 == 0) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
        }
        if (max != 0x80000000) {
            System.out.println("max = " + max);
        } else {
            System.out.println("没有能被13整除的数");
        }

        // min
        int min = 0x7fffffff;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 13 == 0) {
                if (arr[i] < min) {
                    min = arr[i];
                }
            }
        }
        if (min == 0x7fffffff) {
            System.out.println("没有能被13整除的数");
        } else {
            System.out.println("min = " + min);
        }
    }

    public static void max_min2(String[] args) {
        int[] arr = new int[16];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //max
        int max = 0x80000000;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("max = " + max);

        // min
        int min = 0X7fffffff;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("min = " + min);
    }

    public static void min(String[] args) {
        int[] arr = new int[18];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("min = " + min);
    }


    public static void max(String[] args) {
        // System.out.println("哈哈哈");
        int[] arr = new int[19];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 30);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("max = " + max);
    }
}
