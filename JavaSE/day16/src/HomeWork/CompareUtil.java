package HomeWork;

public  class CompareUtil {

    // 冒泡 CompareUtil
    public static void bubble(Comparable[] arr){

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j].compareTo(arr[j+1])>0){
                    Comparable tmp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=tmp;
                }
            }
        }
    }

    // 选择
    public static void select(Comparable[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            int t = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[i])<0){

                    t=j;
                }
            }
            if (t!=i) {
                Comparable tmp = arr[i];
                arr[i] = arr[t];
                arr[t] = tmp;
            }
        }
    }

    // max
    public static Comparable max(Comparable[] arr){
        Comparable max=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(max)>0){
                max = arr[i];
            }
        }
        return  max;
    }

    // min
    public static Comparable min(Comparable[] arr){
        Comparable min=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(min)<0){
                min = arr[i];
            }
        }
        return  min;

    }
}
