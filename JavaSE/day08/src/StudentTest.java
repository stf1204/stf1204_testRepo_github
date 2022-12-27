public class StudentTest {
    public static void main(String[] args) {

    }

    public static void main6(String[] args) {
        Student[] stu = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < stu.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            stu[i] = new Student(id, name, grade, score); //构造对象
        }

        for (int i = 0; i < stu.length; i++) {
            System.out.println(stu[i].say());
        }

        // 找出三年级谁最菜
        Student s3= null;
        for (int i = 0; i < stu.length; i++) {
            if (stu[i].getGrade()==3){  //三年级同学才比较
                if (s3==null){//第一位三年级
                    s3=stu[i];
                }else{ // 后面同学才做比较
                    if (s3.getScore()>stu[i].getScore()){
                        s3=stu[i];
                    }
                }
            }
        }
        if (s3!=null){
            System.out.println("三年级最菜的同学是 = " + s3.say());
        }else{
            System.out.println("没有三年级的同学");
        }


    }

    public static void main7(String[] args) {
        Student[] stu = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < stu.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            stu[i] = new Student(id, name, grade, score); //构造对象
        }

        for (int i = 0; i < stu.length; i++) {
            System.out.println(stu[i].say());
        }

        // 找出三年级最高分
        double s1 = 0x80000000;
        for (int i = 0; i < stu.length; i++) {
            if (stu[i].getGrade() == 3) {
                if (stu[i].getScore() > s1) {
                    s1 = stu[i].getScore();
                }
            }
        }
        if (s1 == 0x80000000) {
            System.out.println("没有三年级的同学");
        } else {
            System.out.println("三年级的最高分为 = " + s1);
        }

        System.out.println("----------------------------------------");

        // 找出三年级谁最牛
        Student s12 = null;  //假定三年级最牛同学不存在。  stu[-1]越界异常
        for (int i = 0; i < stu.length; i++) {
            if (stu[i].getGrade() == 3) {
                if (s12 == null) {
                    s12 = stu[i];
                } else {
                    if (stu[i].getScore() > s12.getScore()) {
                        s12 = stu[i];
                    }
                }
            }
        }
        if (s12 == null) {
            System.out.println("没有三年级的同学");
        } else {
            System.out.println("三年级最高分的同学是 = " + s12.say());
        }
    }

    public static void max_min(String[] args) {
        Student[] stu = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < stu.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            stu[i] = new Student(id, name, grade, score); //构造对象
        }

        for (int i = 0; i < stu.length; i++) {
            System.out.println(stu[i].say());
        }

        // 找出谁最牛牛
        Student s = stu[0];
        for (int i = 0; i < stu.length; i++) {
            if (s.getScore() < stu[i].getScore()) {
                s = stu[i];
            }
        }
        System.out.println("全校最牛 = " + s.say());

        // 找出谁最low
        Student s1 = stu[0];
        for (int i = 0; i < stu.length; i++) {
            if (s1.getScore() > stu[i].getScore()) {
                s1 = stu[i];
            }
        }
        System.out.println("全校最low = " + s1.say());
    }

    public static void max(String[] args) {
        Student[] stu = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < stu.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            stu[i] = new Student(id, name, grade, score); //构造对象
        }

        for (int i = 0; i < stu.length; i++) {
            System.out.println(stu[i].say());
        }

        //找全校最高分
        double max = stu[0].getScore();
        for (int i = 0; i < stu.length; i++) {
            if (max < stu[i].getScore()) {
                max = stu[i].getScore();
            }
        }
        System.out.println("全校最高分= " + max);
    }

    public static void avg(String[] args) {
        Student[] stu = new Student[10];
        String[] name1 = {"孙", "张", "尹", "宋", "韩", "乔", "高", "徐", "廉", "曲", "可", "朱", "卓", "古力", "勇", "哲", "邹", "宋"};
        String[] name2 = {"韦", "刚", "丽", "芳", "非", "飞", "鸣", "明", "陶", "涛", "桃", "杰", "娜", "宇", "雨", "坤", "鹏", "巧"};
        for (int i = 0; i < stu.length; i++) {
            int id = i + 1;
            int index1 = (int) (Math.random() * 10000 % name1.length);
            int index2 = (int) (Math.random() * 10000 % name2.length);
            String name = name1[index1] + name2[index2];
            int grade = (int) (Math.random() * 6 + 1);
            int score = (int) (Math.random() * 100);
            stu[i] = new Student(id, name, grade, score); //构造对象
        }

        for (int i = 0; i < stu.length; i++) {
            System.out.println(stu[i].say());
        }

        // 求全校平均分
        double sum = 0;
        for (int i = 0; i < stu.length; i++) {
            sum += stu[i].getScore();  // 累加操作 是score类型
        }

        System.out.println("******************************");
        double avg = sum / stu.length;
        System.out.println("全校平均分 = " + avg);

        System.out.println("------------------------------------");
        //求三年级平均分
        double sum3 = 0;
        int count3 = 0;
        for (int i = 0; i < stu.length; i++) {
            if (stu[i].getGrade() == 3) {
                sum3 += stu[i].getScore();
                count3++;
            }
        }
        if (count3 == 0) {
            System.out.println("没有三年级同学");
        } else {
            double avg3 = sum3 / count3;
            System.out.println("三年级的平均分为 = " + avg3);
        }

    }

    public static void main1(String[] args) {
        Student[] stu = new Student[5]; //此时数组引用中都为空
        stu[0] = new Student(22, "小白", 2, 92);
        stu[1] = new Student(21, "小黑", 6, 72);
        stu[3] = new Student(25, "小红", 5, 88);
        stu[4] = new Student(29, "小绿", 4, 60);

        //补空洞
        stu[2] = new Student(10, "小蓝", 3, 99);

        for (int i = 0; i < stu.length; i++) {
            if (stu[i] != null) {
                System.out.println(stu[i].say()); //数组元素其实是引用
            } else {
                System.out.println(stu[i]);
            }
        }
    }
}
