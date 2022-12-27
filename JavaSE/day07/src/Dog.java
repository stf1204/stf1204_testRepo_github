public class Dog {
    String name;
    int weight;
    String type;

    public void shout(){
        System.out.println(name+"狗狗在汪汪汪...");
    }

    public String say() {
        String s ="名字："+name+", 体重："+weight+", 品种："+type;
        return s;
    }

    public void eat(String some){
        System.out.println(name+"狗狗在吃"+some);
    }
}
