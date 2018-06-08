import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.StringTokenizer;
import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JuiceIsBest {

    static public void main(String[] arg) {
        ArrayList<ArrayList<String>> juiceList = read();
        ArrayList<String> juiceNeaded = searchJuice(juiceList);
        write(juiceNeaded);
        writeSorted(juiceNeaded);
        Optimization(juiceList, juiceNeaded);
    }

    private static ArrayList<ArrayList<String>> read() {
        ArrayList<ArrayList<String>> juiceList = new ArrayList<ArrayList<String>>();
        StringTokenizer tokenizer;
        try {
            Scanner scanner = new Scanner(new File("Juice.in"));
            while (scanner.hasNextLine()) {
                tokenizer = new StringTokenizer(scanner.nextLine());
                ArrayList<String> tempList = new ArrayList<String>();
                while (tokenizer.hasMoreTokens()) {
                    tempList.add(tokenizer.nextToken());
                }
                juiceList.add(tempList);
            }
        } catch (IOException a) {
            System.out.println(a);
        }
        return juiceList;
    }

    private static ArrayList<String> searchJuice(ArrayList<ArrayList<String>> juiceList) {
        ArrayList<String> neadedJuice = new ArrayList<String>();
        for (ArrayList<String> aJuiceList : juiceList) {
            for (String anAJuiceList : aJuiceList) {
                if (!neadedJuice.contains(anAJuiceList)) {
                    neadedJuice.add(anAJuiceList);
                }
            }
        }
        return neadedJuice;
    }

    private static void write(ArrayList<String> juiceNeaded) {
        try {
            FileWriter output = new FileWriter("juice1.out");
            for (String aJuiceNeaded : juiceNeaded) {
                output.write(aJuiceNeaded + "\n");
            }
            output.close();
        } catch (IOException a) {
            System.out.println(a);
        }
    }

    private static void writeSorted(ArrayList<String> juiceNeaded) {
        try {
            Thread flow = new Thread(() -> {
                juiceNeaded.sort(Comparator.naturalOrder());
            });
            flow.start();
            flow.join();
            FileWriter output = new FileWriter("juice2.out");
            for (String aJuiceNeaded : juiceNeaded) {
                output.write(aJuiceNeaded + "\n");
            }
            output.close();
        } catch (IOException | InterruptedException a) {
            System.out.println(a);
        }
    }

    private static void Optimization(ArrayList<ArrayList<String>> juiceList, ArrayList<String> juiceNeaded) {
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<Integer> order = new ArrayList<Integer>();
        GenerationOrder(order, 0, 0, juiceList.size());
        int min = Calculate(juiceList, order.get(0), juiceNeaded);
        for (int i = 1; i < order.size(); i++) {
            min = Math.min(min, Calculate(juiceList, order.get(i), juiceNeaded));
        }
        try {
            FileWriter output = new FileWriter("juice3.out");
            output.write("" + min);
            output.close();
        } catch (IOException a) {
            System.out.println(a);
        }
    }

    private static void GenerationOrder(ArrayList<Integer> order, int temp, int local, int size) {
        if (local == size) {
            order.add(temp);
            return;
        }
        for (int i = 1; i <= size; i++) {
            int j = 1;
            boolean flag = true;
            while (j <= temp) {
                if (i == temp / j % 10) {
                    flag = false;
                }
                j *= 10;
            }
            if (flag) {
                GenerationOrder(order, (temp * 10 + i), (local + 1), size);
            }
        }
    }

    private static int Calculate(ArrayList<ArrayList<String>> juiceList, int order, ArrayList<String> juiceNeaded) {
        int min = 0;
        int step10 = 1;
        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < juiceList.size(); i++) {
            int step = order / step10 % 10 - 1;
            for (int j = 0; j < juiceNeaded.size(); ) {
                if ((temp.contains(juiceNeaded.get(j))) && (!juiceList.get(step).contains(juiceNeaded.get(j)))) {
                    temp.clear();
                    min++;
                    j = 0;
                    continue;
                }
                if ((!temp.contains(juiceNeaded.get(j))) && (juiceList.get(step).contains(juiceNeaded.get(j)))) {
                    temp.add(juiceNeaded.get(j));
                }
                j++;
            }
            step10 *= 10;
        }
        min++;
        return min;
    }
}
