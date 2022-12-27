import java.util.Arrays;
import java.util.List;

public class arraysTest {

    public static void main(String[] args) {
        int [] arr = new int[]{1,2,3,4,5,6,1,23};
        // sort()
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println("________");

        // binarySearch()
        int s = Arrays.binarySearch(arr,2);
        System.out.println("s = " + s);
        System.out.println("________");

        // array To list
        List<Integer> ints = Arrays.asList(1,2,3,4,5,2,5,4,2);
        System.out.println(ints);

    }
}
