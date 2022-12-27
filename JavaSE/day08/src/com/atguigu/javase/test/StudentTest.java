package com.atguigu.javase.test;

import com.atguigu.javase.javabean.Student;

public class StudentTest {

    public static void quick(int[] arr, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex - 1) {
            return;
        }
        int key = arr[beginIndex]; // 取key值 全程需要进行比较的值
        int keyIndex = beginIndex; // 在key的右边依次摆放比key小的值
        for (int i = beginIndex; i < endIndex; i++) {
            if (arr[i] < key) {
                keyIndex++;//右移放值
                int tmp = arr[keyIndex];
                arr[keyIndex] = arr[i];
                arr[i] = tmp;
            }
        }

        //key归位，将key值与最后一个比自己小的值位置进行交换
        arr[beginIndex] = arr[keyIndex];
        arr[keyIndex] = key;

        quick(arr, beginIndex, keyIndex);  //递归左
        quick(arr, keyIndex + 1, endIndex); //递归右
    }


    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * arr.length);
        }
        quick(arr, 0, arr.length); // 快速排序  分区 + 递归
    }

    public static void main12(String[] args) {
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }
        //bian li
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        //mao pao                  冒泡排序是 我只要找到比我小的，就进行交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("************************************************");

        // xuan ze pai xu          选择排序是 先找到此刻最小值，最后再进行交换
        for (int i = 0; i < arr.length - 1; i++) {
            int minindex = i; //假设第一个位置为最小值 真的吗？ 拿后面的值跟他比较
            for (int i1 = i + 1; i1 < arr.length; i1++) {
                if (arr[i1] < arr[minindex]) {  //有比他更小的
                    minindex = i1;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[minindex];   // 把假设变成现实
            arr[minindex] = tmp;
        }
        // 遍历
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("*************************************************");

        Student[] s1 = new Student[10];
        //姓数组
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        //名数组
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            // 随机姓名
            String name = name1[index1] + name2[index2];
            int id = i + 1;
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            //构造对象
            s1[i] = new Student(id, name, grade, score);
        }
        // bian li
        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        // 学生对象冒泡排序
        for (int i = 0; i < s1.length - 1; i++) {
            for (int j = 0; j < s1.length - 1 - i; j++) {
                if (s1[j].getScore() > s1[j + 1].getScore()) {  // 只要找到比我小  我就交换
                    Student tmp = s1[j];
                    s1[j] = s1[j + 1];
                    s1[j + 1] = tmp;
                }
            }
        }
        // bian li
        for (Student student : s1) {
            System.out.println(student.say());
        }
        System.out.println("*******************************");

        // 学生对象选择排序
        for (int i = 0; i < s1.length - 1; i++) {  // 0~n-1的位置需要确定最小值
            int minindex = i;   //假设刚开始该位置为最小值  对吗？ 与后面的值进行比较
            for (int j = i + 1; j < s1.length; j++) {  // i+1~n位置 每趟依次找出当前最小值的下标
                if (s1[minindex].getScore() > s1[j].getScore()) {
                    minindex = j;
                }
            }
            Student tmp = s1[minindex];
            s1[minindex] = s1[i];     // 最小值前移
            s1[i] = tmp;
        }
        // bain li
        for (Student student : s1) {
            System.out.println(student.say());
        }
        System.out.println("*****************************************");


    }

    public static void main11(String[] args) {

        Student[] s1 = new Student[10];
        //姓数组
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        //名数组
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            // 随机姓名
            String name = name1[index1] + name2[index2];
            int id = i + 1;
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            //构造对象
            s1[i] = new Student(id, name, grade, score);
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        // 学生对象冒泡排序
        for (int i = 0; i < s1.length - 1; i++) {
            for (int i1 = 0; i1 < s1.length - 1 - i; i1++) {
                if (s1[i1].getScore() > s1[i1 + 1].getScore()) {
                    Student tmp = s1[i1];
                    s1[i1] = s1[i1 + 1];
                    s1[i1 + 1] = tmp;
                }
            }
        }
        for (Student student : s1) {
            System.out.println(student.say());
        }
        System.out.println("****************************************");


    }

    public static void main10(String[] args) {
        Student[] s1 = new Student[10];
        //姓数组
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        //名数组
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            // 随机姓名
            String name = name1[index1] + name2[index2];
            int id = i + 1;
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            //构造对象
            s1[i] = new Student(id, name, grade, score);
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        // 删除指定位置元素 学号6
        for (int i = 5; i < s1.length - 1; i++) { //判断条件为 i<10-1 => i<9
            s1[i] = s1[i + 1]; //最后一个  i=8 => i+1=9 => s1[9]为第十个元素
        }
        s1[s1.length - 1] = null;  // 最后一个元素赋值给倒数第二个   因此会重复
        for (int i = 0; i < s1.length; i++) {
            if (s1[i] != null) {
                System.out.println(s1[i].say());
            } else {
                System.out.println(s1[i]);
            }
        }

    }

    public static void main9(String[] args) {
        Student[] s1 = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            s1[i] = new Student(id, name, grade, score); //构造对象
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        // 获取三年级同学的分数

        Student[] s2 = new Student[s1.length];//新数组  容量足够大
        // 算法灵魂，计数器
        int count = 0;
        for (int i = 0; i < s1.length; i++) {
            if (s1[i].getGrade() == 3) {
                s2[count] = s1[i];
                count++;
            }
        }
        Student[] s3 = new Student[count];
        for (int i = 0; i < count; i++) {
            s3[i] = s2[i];
        }

        s1 = s3;

        for (Student student : s1) {
            System.out.print(student.say() + "    ");
        }
        System.out.println();
    }

    public static void main8(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }
        //bian li
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] arr2 = new int[arr.length];
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                arr2[count] = arr[i];
                count++;
            }
        }
        int[] arr3 = new int[count];
        for (int i = 0; i < count; i++) {
            arr3[i] = arr2[i];
        }

        //bian li
        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();
        System.out.println("****************************");

    }

    public static void main7(String[] args) {
        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }
        //遍历
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] arr2 = new int[arr.length]; //arr2

        int count = 0;
        for (int i : arr) {  //取奇数
            if (i % 2 != 0) {
                arr2[count] = i;
                count++;
            }
        }

        int[] arr3 = new int[count];  // 裁剪
        for (int i = 0; i < count; i++) {
            arr3[i] = arr2[i];
        }
        arr = arr3;   //更换指向

        // 遍历
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.println("********************************");
    }

    public static void main6(String[] args) {

        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }

        //遍历
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        //扩容
        int[] arr2 = new int[(int) (arr.length * 1.5)];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = arr[i];
        }
        arr = arr2;

        // 遍历
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("**********************************************");
        Student[] s1 = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            s1[i] = new Student(id, name, grade, score); //构造对象
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        // 扩容1.5倍
        Student[] s2 = new Student[(int) (s1.length * 1.5)];
        for (int i = 0; i < s1.length; i++) {
            s2[i] = s1[i];
        }
        s1 = s2;

        for (Student student : s1) {
            if (student != null) {
                System.out.println(student.say());
            } else {
                System.out.println(student);
            }
        }
        System.out.println("*****************************************************");
    }

    public static void main5(String[] args) {

        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }

        //遍历
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 缩减
        int[] arr2 = new int[arr.length / 2];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = arr[i];
        }
        arr = arr2;

        // 遍历
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("*********************************");

        Student[] s1 = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            s1[i] = new Student(id, name, grade, score); //构造对象
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");
        Student[] s2 = new Student[s1.length / 2];  //1 创建新数组，容量减半，里面是空洞
        for (int i = 0; i < s2.length; i++) {   //2 依次把老数组中有用的数字复制到新数组中
            s2[i] = s1[i];
        }
        s1 = s2; //3、老数组指向新数组
        for (Student student : s1) {
            System.out.println(student.say());
        }
    }

    public static void main4(String[] args) {

        int[] arr = new int[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 40);
        }

        //遍历
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 复制
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }

        //遍历
        for (int i : copy) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("**************************************************");
        Student[] s1 = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            s1[i] = new Student(id, name, grade, score); //构造对象
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        // 复制
        Student[] s2 = new Student[s1.length]; // 此时s2中全是空洞
        for (int i = 0; i < s2.length; i++) {
            s2[i] = s1[i];
        }

        // 遍历
        for (Student student : s2) {
            System.out.println(student.say());
        }
        System.out.println("*****************************");

    }

    public static void main3(String[] args) {
        Student[] s1 = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            s1[i] = new Student(id, name, grade, score); //构造对象
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        // 反转
        for (int i = 0; i < s1.length / 2; i++) {
            Student tmp = s1[i];
            s1[i] = s1[s1.length - 1 - i];
            s1[s1.length - 1 - i] = tmp;
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
    }

    public static void main2(String[] args) {
        Student[] s1 = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            s1[i] = new Student(id, name, grade, score); //构造对象
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");

        //用下标找出全校谁最牛
        int maxindex = 0; //假定0位置同学最牛
        // 进一步比较和刷新
        for (int i = 0; i < s1.length; i++) {
            if (s1[maxindex].getScore() < s1[i].getScore()) {
                maxindex = i;
            }
        }
        System.out.println("最牛学生为 = " + s1[maxindex].say());
        System.out.println("最牛学生下标为 = " + maxindex);
    }

    public static void main1(String[] args) {
        Student[] s1 = new Student[100];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < s1.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            s1[i] = new Student(id, name, grade, score); //构造对象
        }

        for (Student tmp : s1) {
            System.out.println(tmp.say());
        }
        System.out.println("**************************************************");


        Student max = s1[0];
        Student min = s1[0];

        // max
        for (Student tmp : s1) {
            if (tmp.getScore() > max.getScore()) {
                max = tmp;
            }
        }

        //  min
        for (Student tmp : s1) {
            if (tmp.getScore() < min.getScore()) {
                min = tmp;
            }
        }

        System.out.println("max = " + max.say());
        System.out.println("max = " + min.say());

        System.out.println("___________________________________________");


        // 三年级最牛和最菜同学
        Student s2 = null;
        Student s3 = null;

        // 三年级最牛
        for (Student sn : s1) {
            if (sn.getGrade() == 3) {
                if (s2 == null) {
                    s2 = sn;
                } else {
                    if (sn.getScore() > s2.getScore()) {
                        s2 = sn;
                    }
                }
            }
        }
        if (s2 == null) {
            System.out.println("没有三年级的同学");
        } else {
            System.out.println("三年级最牛的同学是= " + s2.say());
        }
        System.out.println("-------------------------------------------------");

        //三年级最菜
        for (Student sn : s1) {
            if (sn.getGrade() == 3) {
                if (s3 == null) {
                    s3 = sn;
                } else {
                    if (s3.getScore() > sn.getScore()) {
                        s3 = sn;
                    }
                }
            }
        }
        if (s3 == null) {
            System.out.println("没有三年级的同学");
        } else {
            System.out.println("三年级最菜的同学是 = " + s3.say());
        }
    }
}
