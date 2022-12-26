public class dogTest {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.type="哈士奇";
        System.out.println("type=" + d.type);
        d.weight=10;
        System.out.println("weight = " + d.weight);
        d.name="二哈";
        System.out.println("name = " + d.name);

        d.eat("肉");
        d.shout();
        System.out.println("~~~~~~~~");
        String s=d.say();
        System.out.println(s);
    }
}
