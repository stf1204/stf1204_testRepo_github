public class StudentTest {
    public static void main(String[] args) {
        System.out.println("--------------------------------");
        Student s0 = new Student();
        s0.say();
        System.out.println("--------------------------------");
        Student s1 = new Student(2001,99);
        s1.say();
        System.out.println("--------------------------------");
        Student s2 = new Student(1001,"xiaoming",6,100);
        s2.say();
        System.out.println("--------------------------------");
    }
    public static void main1(String[] args) {
        Student s1 = new Student();
        s1.setId(1001);
        s1.setName("小明");
        s1.setGrade(5);
        s1.setScore(99);
        System.out.println("grade:"+s1.getGrade());
        System.out.println("id:"+s1.getId());
        System.out.println("name:"+s1.getName());
        System.out.println("score:"+s1.getScore());

        System.out.println(s1.say());
        s1.study();
    }
}
