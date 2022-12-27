public class Home {
    public static void main(String[] args) {

    }
    //有条件(%7==0)的平均值 最大值 最小值 有条件(%7==0)的最大值，最小值下标，反转，冒泡

    public static void mao_pao_2(String[] args) {
        int[] arr = new int[8];

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }


        //遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //mao_pao
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void reverse2(String[] args) {
        int[] arr = new int[8];

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 30);
        }

        //遍历打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //reverse
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }

        // bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void mao_pao(String[] args) {
        int[] arr = new int[8];

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 30);
        }

        //遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //mao_pao
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        // bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void reverse(String[] args) {
        int[] arr = new int[8];

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        // 遍历打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();


        //reverse
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - i - 1] = tmp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void min_index(String[] args) {
        int[] arr = new int[8];

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 30);
        }

        //遍历打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();


        //最小值索引
        int minindex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {
                if (minindex == -1) {
                    minindex = i;
                } else {
                    if (arr[minindex] > arr[i]) {
                        minindex = i;
                    }
                }
            }
        }
        if (minindex == -1) {
            System.out.println("数组中没有能被7整除的数");
        } else {
            System.out.println("minindex = " + minindex);
            System.out.println("数组中能将7整除的最小整数为 = " + arr[minindex]);
        }
    }

    public static void max_index(String[] args) {
        int[] arr = new int[8];
        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 30);
        }

        //遍历打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int maxindex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {
                if (maxindex == -1) {
                    maxindex = i;
                } else {
                    if (arr[maxindex] < arr[i]) {
                        maxindex = i;
                    }
                }
            }
        }
        if (maxindex == -1) {
            System.out.println("数组中没有能被7整除的数");
        } else {
            System.out.println("maxindex = " + maxindex);
            System.out.println("能被7整除的最大的那个数为 = " + arr[maxindex]);
        }
    }

    public static void min(String[] args) {
        int[] arr = new int[8];

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        //遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //有条件的最小值
        int min = 0x7fffffff;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {
                if (min == 0x7fffffff) {
                    min = arr[i];
                } else {
                    if (arr[i] < min) {
                        min = arr[i];
                    }
                }
            }
        }
        if (min == 0x7fffffff) {
            System.out.println("数组中没有能被7整除的数据");
        } else {
            System.out.println("min:" + min);
        }
    }

    public static void max(String[] args) {
        //有条件的max
        int[] arr = new int[8];

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        // 遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        //有条件的max
        int max = 0x80000000;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {
                if (max == 0x80000000) {
                    max = arr[i];
                } else {
                    if (max < arr[i]) {
                        max = arr[i];
                    }
                }
            }
        }
        if (max == 0x80000000) {
            System.out.println("数组中没有被7整除的数");
        } else {
            System.out.println(max);
        }
    }

    public static void avg(String[] args) {
        //有条件(%7==0)的平均值
        int[] arr = new int[8];
        int sum = 0;
        int count = 0;

        //初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 20);
        }

        // 遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 7 == 0) {
                sum += arr[i];
                count++;
            }
        }
        if (count != 0) {
            int avg = sum / count;
            System.out.println("avg=" + avg);
        } else {
            System.out.println("数组中没有能被7整除的数");
        }
    }


}
