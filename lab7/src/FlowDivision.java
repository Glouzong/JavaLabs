import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlowDivision {
    public static void main(String[] arg) {
        ArrayList<String> out1 = new ArrayList<String>();
        ArrayList<String> out2 = new ArrayList<String>();
        reader(out1,out2);
        writer(out1, out2);
    }

    private static void reader(ArrayList<String> out1, ArrayList<String> out2) {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            Pattern pattern1 = Pattern.compile("^([0-9]+)$");
            Pattern pattern2 = Pattern.compile("[1-9]");
            while (scanner.hasNextLine()) {
                String buffer = scanner.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(buffer, "\t\n\r ,.");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    Matcher matcher = pattern1.matcher(token);
                    if (matcher.find()) {
                        out1.add(token);
                    }
                    matcher = pattern2.matcher(token);
                    if (!matcher.find()) {
                        out2.add(token);
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private static void writer(ArrayList<String> out1, ArrayList<String> out2) {
        try {
            FileWriter output1, output2;
            output1 = new FileWriter("output1.txt");
            output2 = new FileWriter("output2.txt");
            System.out.println(out1.size());
            System.out.print(out2.size());
            out1.sort(Comparator.naturalOrder());
            out2.sort(Comparator.naturalOrder());
            for (String anOut1 : out1) {
                output1.write(anOut1 + " ");
            }
            for (String anOut2 : out2) {
                output2.write(anOut2 + " ");
            }
            output1.close();
            output2.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
