public class Student {
    //属性 int id,String name,int grade, double score
    // 属性要封装，提供get，set方法 set中加入对年级和分数的合法判断
    // 年级[1~6] 分数[0~100] 使用this关键字
    // 在测试类中创建一个对象，把对象的所有属性都赋值，并打印对象的详细信息

    private int id;
    private String name;
    private int grade = 1;
    private double score = 60;

    public Student() {  //无参构造器
        this(1001, 6);
        System.out.println("我是无参构造器，仅仅完成初始化操作");
    }

    public Student(int id, int grade) {
        this(id, "xiaoli", grade, 100);
        // this.id = id;
        // this.grade =grade;
        // this.score =100;
        // this.name="xiao li";
        System.out.println("int int ");
    }

    public Student(int id, String name, int grade, double score) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.score = score;
        System.out.println("int String int double");
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        if (grade > 6 || grade < 1) {
            return;
        }
        this.grade = grade;
    }

    public void setScore(int score) {
        if (score > 100 || score < 0) {
            return;
        }
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public double getScore() {
        return score;
    }

    public String say() {
        String s = "学号:" + id + ",姓名:" + name + ",年级:" + grade + ",分数:" + score;
        return s;
    }

    public void study() {
        System.out.println(name + "正在学习");
    }


}
