/**
 * @author stf
 */
class TestException2 {

    public static int divide(int x, int y) throws IllegalNumberException {
        try {
            return x / y;
        } catch (Exception e) {
            throw new IllegalNumberException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("main begin....");
        try {
            System.out.println(divide(3, 2));
            System.out.println(divide(3, 0));
        } catch (IllegalNumberException e) {
            // System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("main end....");
    }
}


public class TestException {

    public static int divide(int m, int n) throws IllegalNumberException {
        if (n == 0) {
            throw new IllegalNumberException("IllegalNumberException");
        }
        return m / n;

    }

    public static void main(String[] args) {

        System.out.println("main begin ...");
        try {
            System.out.println(divide(3, 2));
            System.out.println(divide(3, 0));
        } catch (IllegalNumberException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally一定要执行");
        }
        System.out.println("main end ...");
    }
}
