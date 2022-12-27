package HomeWork;

class Student implements Comparable{
    private String name;
    private  int id;
    private int grade;
    private double score;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Student() {
    }

    public Student(String name, int id, int grade, double score) {
        this.name = name;
        this.id = id;
        this.grade = grade;
        this.score = score;
    }

    @Override
    // 按照成绩
    public int compareTo(Object o) throws RuntimeException{
        // if (this.score>((Student)o).score){
        //     return 1;
        // }else if (this.score<((Student)o).score){
        //     return -1;
        // }else{return 0;}
        if (o instanceof Student) {
            return (int) (this.score * 100 - ((Student) o).score * 100);
        }else{
            throw new RuntimeException("比较的对象不对，请重新输入");
        }
    }


    /* 按照年级
    public int compareTo(Object o){
        return this.grade-((Student)o).grade;
    }

     */
}


interface test{
    public void sat();
}

public class CompareTest {
    public static void main2(String[] args) {
        test t1 = new test() {
            @Override
            public void sat() {
                System.out.println("匿名内部类");
            }
        };

        t1.sat();
    }

    public static void main(String[] args) {
        Student[] arr= new Student[5];
        arr[0] = new Student("小工",1001,5,80);
        arr[1] = new Student("小红",1101,4,82);
        arr[2] = new Student("小丽",1201,6,52);
        arr[3] = new Student("小芳",1301,3,62);
        arr[4] = new Student("小飞",1401,2,92);
        System.out.println(arr[0].compareTo(arr[2]));

        // 遍历
        for (Student student : arr) {
            System.out.println(student);
        }
        System.out.println("****************************");

        //冒泡
        /*
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j].compareTo(arr[j+1])>0){
                    Student tmp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }

         */
        CompareUtil.bubble(arr);

        System.out.println("****************************");
        for (Student student : arr) {
            System.out.println(student);
        }

        System.out.println("****************************");
        String[] s2 = {"0214da","AAAA","aaa","bbb","哈","嘻嘻","123","开怀大笑"};
        CompareUtil.bubble(s2);

        System.out.println("********************************");
        for (String s : s2) {
            System.out.println(s);
        }

        System.out.println("----------------------------------");
        CompareUtil.select(arr);

        for (Student student : arr) {
            System.out.println(student);
        }

        System.out.println("====================================");
        System.out.println("max = "+ CompareUtil.max(arr));
        System.out.println("max = "+ CompareUtil.max(s2));
        System.out.println("`````````````````````````````````````");
        System.out.println("min = "+ CompareUtil.min(arr));
        System.out.println("min = "+ CompareUtil.min(s2));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        try {
            System.out.println(arr[2].compareTo("aaa"));
        }catch (RuntimeException e){
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    }

