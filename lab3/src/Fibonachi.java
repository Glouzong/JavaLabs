import java.util.Scanner;
import java.util.Vector;

public class Fibonachi {
    public static void main(String[] args) {
        Vector<Integer> series = new Vector();
        int n = input(series);
        generateFibonachi(series, n);
        output(series);

    }

    static int input(Vector<Integer> series) {
        System.out.println("Введите первые два элемента x0, x1 и длину ряда n");
        Scanner scan = new Scanner(System.in);

        series.add(scan.nextInt());
        series.add(scan.nextInt());
        return scan.nextInt();
    }

    static void generateFibonachi(Vector<Integer> series, int n) {
        for (int i = 2; i < n; i++) {
            series.add(series.get(i - 1) + series.get(i - 2));
        }
    }

    static void output(Vector<Integer> series) {
        for (int i = 0; i < series.size(); i++) {
            System.out.print(series.get(i) + " ");
        }
    }
}