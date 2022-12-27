public class ExceptionTest {

    public static void main(String[] args) {
        System.out.println("main begin...");

        boolean b2 = true;
        while (b2){
            return;  // 还未进入try  return就已经将main弹出了,不会执行finally了。
        }

        try {
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            int n3 = n1 / n2;
            System.out.println(n1 + " / " + n2 + " = " + n3);

            boolean b = true;
            while (b){
                return;  // try catch 中return也拦不住finally  总要执行的
            }
        }catch (NumberFormatException e){
            System.out.println(e);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }catch (ArithmeticException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println();

        }finally{
            System.out.println("无论如何，我一定要执行！、、、");
        }
            System.out.println("main end...");

    }
}


