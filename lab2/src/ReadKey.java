import java.util.Random;
import java.util.Scanner;

public class ReadKey {
    final static int MAX_SIZE = 100;

    public static void main(String[] args) {
        String string = new String();
        string = getInput();
        double[] numbers = {0, 0, 0};
        try {
            getNumbers(numbers, string);
            getArray(numbers);
            System.out.println();
            System.out.println("Average: " + getAverage(numbers));
            System.out.println("Min: " + getMin(numbers));
            System.out.println("Max: " + getMax(numbers));
            int min = (int) getMin(numbers);
            int[] array = generation(min);
            sortBubble(array);
            getArray(array);
            System.out.println();
            getPrimenumber(array);
            getNumberDev3(array);
        } catch (Exception a) {
            System.out.println("Мало чисел");
        }
    }

    static void sortBubble(int[] array) {
        int i, j;
        for (i = array.length - 1; i >= 0; i--) {
            for (j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static void getPrimenumber(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            if (itIsPrimeNumber(array[i])) {
                temp++;
            }
        }
        System.out.println("Количество простых чисел: " + temp);
        System.out.println();
    }

    static void getNumberDev3(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i]) % 3 == 0) {
                temp++;
            }
        }
        System.out.println("Количество кратных 3: " + temp);
        System.out.println();
    }

    static boolean itIsPrimeNumber(int n) {
        boolean f = true;
        int i;
        if ((n == 0) || (n == 1))
            f = false;
        for (i = 2; i < n; i++) {
            if (n % i == 0) {
                f = false;
                break;
            }
        }
        if (f) return true;
        else return false;
    }

    static int[] generation(int min) {
        Random rand = new Random();
        int[] array = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            array[i] = rand.nextInt(min + 1);
        }
        return array;
    }

    static String getInput() {
        System.out.println("Enter 3 number:");
        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        return string;
    }

    static void getArray(int[] numbers) {
        for (int item : numbers) {
            System.out.print(item + " ");
        }
    }

    static void getArray(double[] numbers) {
        for (double item : numbers) {
            System.out.print(item + " ");
        }
    }

    static void getNumbers(double[] numbers, String string) throws Exception {
        int index = 0;
        int indicator = 0;  //0 -не было числа, 1 - число было найденно(идёт целая часть),
        // 3 и больше - число было найденно(идёт дробная часть и её разрядность), 2 - число бинарное.
        char temp;
        int returnbool;
        string += "a";
        char[] chArray = string.toCharArray();
        int count = 0;
        for (; (count < chArray.length) && (index < 3); count++) {
            temp = chArray[count];
            if ((chArray[count] == ']') && (chArray[count - 1] == '[')) {
                indicator = 0;
                continue;
            }
            if (indicator == 2) {
                if ((temp == '1') || (temp == '0')) { //бинарка
                    numbers[index] = numbers[index] * 2 + ((int) temp - '0');
                    continue;
                } else {
                    if (temp == '[') {
                        returnbool = count - 1;
                        numbers[index] = 0;
                        while (chArray[returnbool] != '[') {
                            returnbool--;
                        }
                        for (returnbool++; returnbool < count; returnbool++) {
                            numbers[index] = numbers[index] * 10 + ((int) chArray[returnbool] - '0');
                        }
                        indicator = 2;
                        continue;
                    }
                    if ((numbers[index] != 0) && (chArray[count] != ']')) {
                        returnbool = count;
                        numbers[index] = 0;
                        while (chArray[returnbool] != '[') {
                            returnbool--;
                        }
                        for (returnbool++; returnbool < count; returnbool++) {
                            numbers[index] = numbers[index] * 10 + ((int) chArray[returnbool] - '0');
                        }
                        indicator = 1;
                    } else {
                        if ((temp == ']') && (indicator == 2)) {
                            indicator = 0;
                            index++;
                            continue;
                        }
                        if (chArray[count - 1] == '0') {
                            indicator = 1;
                        } else {
                            indicator = 0;
                        }
                    }
                }
            }
            if ((temp <= '9') && (temp >= '0') && (indicator < 2)) {//обычные числа
                if (indicator == 0) {
                    indicator++;
                }
                numbers[index] = numbers[index] * 10 + ((int) temp - '0');
                continue;
            }
            if ((temp <= '9') && (temp >= '0') && (indicator >= 3)) {//дробные числа
                indicator++;
                numbers[index] = numbers[index] + ((int) temp - '0') / Math.pow(10, indicator - 3);
                continue;
            }
            if ((temp == '[') && (indicator != 0)) {
                if (indicator >= 3) {
                    numbers[index] = 0;
                    index--;
                }
                indicator = 2;
                index++;
                continue;
            }
            if ((temp == '[') && (indicator == 0)) {
                indicator = 2;
                continue;
            }
            if ((temp == '.') && (indicator == 1)) {
                indicator = 3;
                continue;
            }
            if (((temp < '0') || (temp > '9')) && (indicator != 0)) {
                if (indicator >= 3) {
                    numbers[index] = 0;
                    indicator = 0;
                } else {
                    index++;
                    indicator = 0;
                }
            }
        }
        if ((index < 2) || ((index == 2) && (chArray[count - 1] != '0'))) {
            throw new Exception();
        }
    }

    static double getAverage(double[] numbers) {
        double sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum / numbers.length;
    }

    static double getMax(double[] numbers) {
        double max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
        }
        return max;
    }

    static double getMin(double[] numbers) {
        double min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        return min;
    }
}
