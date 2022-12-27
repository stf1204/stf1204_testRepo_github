public class Girl {
    private String name;
    public Girl(){
        this("小红");
    }
    public Girl(String name){
        this.name=name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void marry(Boy boy){
        System.out.println(this.say()+"要嫁给"+boy.say());
    }
    public String say(){
        return"女孩,姓名:"+name;
    }
}
