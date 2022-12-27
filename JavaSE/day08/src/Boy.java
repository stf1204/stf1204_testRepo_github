public class Boy {
    private String name;
    private int age;
    public Boy(){
        this("小刚",12);
    }
    public Boy(String name){
        this(name,18);
    }
    public Boy(String name,int age){
        this.name=name;
        this.age=age;
    }
    public void setName(String name){
        this.name=name;
    }
    public  void  setAge(int age){
        this.age=age;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public  void marry(Girl girl){
        System.out.println(this.say()+"要娶"+girl.say());
        girl.marry(this);
    }
    public String say(){
        return "男孩,姓名:"+name+",年龄"+age;
    }
    public void shout(){
        System.out.println(name+"正在大叫");
    }
}

