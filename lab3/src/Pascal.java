
import java.math.BigInteger;
import java.util.Scanner;

public class Pascal {
    private int n;

    public static void main(String[] args) {
        int n = input();
        pascal(n);
    }

    static void pascal(int last) {
        BigInteger temp;
        for (long n = 0; n <= last; n++) {
            for (long k = 0; k <= n; k++) {
                temp = BigInteger.valueOf(1);
                temp = temp.multiply(factorial(n));
                temp = temp.divide(factorial(n - k));
                temp = temp.divide(factorial(k));
                System.out.print(temp + " ");
            }
            System.out.println();
        }
    }

    static BigInteger factorial(long a) {
        BigInteger temp = BigInteger.valueOf(1);
        for (long i = 1; i <= a; i++) {
            temp = temp.multiply(BigInteger.valueOf(i));
        }
        return temp;
    }

    static int input() {
        while (true) {
            int n;
            System.out.println("Введите количество строк треугольника паскаля от 1");
            Scanner scan = new Scanner(System.in);
            n = scan.nextInt();
            if (1 <= n)
                return n;
        }
    }
}
