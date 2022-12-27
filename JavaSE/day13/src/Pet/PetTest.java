package Pet;

public class PetTest {
    public static void main(String[] args) {
        Pet[] p = new Pet[6];
        p[0] = new Bird("拉拉", 1, 13, 22);
        p[1] = new Bird("((*′д｀)", 1, 12, 12);
        p[2] = new Dog("小溪", 2, 17, "金毛");
        p[3] = new Dog("小发", 2, 23, "金毛");
        p[4] = new Cat("小①", 2, 16, "黄色");
        p[5] = new Cat("回忆", 2, 3, "蓝色");

        // 选择排序
        for (int i = 0; i < p.length-1; i++) {
            int max =i;
            for (int j = i+1; j < p.length; j++) {
                if (p[j].getWeight()>p[max].getWeight()){
                    max=j;
                }
            }
            Pet tmp = p[max];
            p[max]=p[i];
            p[i]=tmp;
        }

         /*
        //  冒泡排序
        for (int i = 0; i < p.length-1; i++) {
            for (int j = 0; j < p.length-1; j++) {
                if (p[j].getWeight()<p[j+1].getWeight()){
                    Pet tmp = p[j];
                    p[j]=p[j+1];
                    p[j+1]=tmp;
                }
            }
        }
          */


        for (Pet pet : p) {
            System.out.println(pet);
        }

    }
}
