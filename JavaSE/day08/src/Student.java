public class Student {
    private int id;
    private String name;
    private int grade;
    private double score;

    public Student(){}
    public Student(int id,String name,int grade,double score){
        this.id=id;
        this.name=name;
        this.grade=grade;
        this.score=score;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setGrade(int grade){
        this.grade=grade;
    }
    public void setScore(double score){
        this.score=score;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getGrade(){
        return grade;
    }
    public double getScore(){
        return score;
    }
    public String say(){
        String s = "学号:"+id+",姓名:"+name+",班级："+grade+",分数:"+score;
        // System.out.println(s);
        return s;
    }
}
