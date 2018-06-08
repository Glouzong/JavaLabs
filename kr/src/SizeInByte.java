import javax.print.attribute.standard.MediaSize;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SizeInByte {
    public static void main(String arg[]) {
        ArrayList<String> arrays = new ArrayList<String>();
        ArrayList<MyArray> parsArray = new ArrayList<MyArray>();
        try {
            File byter = new File("bytes.in");
            Scanner scanner = new Scanner(byter);
            while (scanner.hasNextLine()) {
                arrays.add(scanner.nextLine());
            }
            parsing(arrays, parsArray);
        } catch (IOException a) {
            System.out.println(a);
        }
    }

    static public class MyArray {
        int type;
        StringBuffer name;
        int size;

        MyArray() {
            type = 0;
            name = new StringBuffer();
            size = 0;
        }

        public void setType(int newType) {
            type = newType;
        }

        public void setName(String newName) {
            name.append(newName);
        }

        public void setSize(int a) {
            size = a;
        }
    }

    public static Comparator<MyArray> TypeComparator = new Comparator<MyArray>() {
        @Override
        public int compare(MyArray e1, MyArray e2) {
            return e1.type - e2.type;
        }
    };

    public static Comparator<MyArray> NameComparator = new Comparator<MyArray>() {
        @Override
        public int compare(MyArray e1, MyArray e2) {
            return e1.name.toString().compareTo(e2.name.toString());
        }
    };

    static void writer(ArrayList<MyArray> parsArray) {
        try {
            FileWriter bytes3 = new FileWriter("bytes3.out");
            for (int i = 0; i < parsArray.size(); i++) {
                bytes3.write(parsArray.get(i).size + "\n");
            }
            bytes3.close();
            parsArray.sort(NameComparator);
            FileWriter bytes1 = new FileWriter("bytes1.out");
            for (int i = 0; i < parsArray.size(); i++) {
                bytes1.write(parsArray.get(i).name + "\n");
            }
            bytes1.close();
            FileWriter bytes2 = new FileWriter("bytes2.out");
            parsArray.sort(TypeComparator);
            for (int i = 0; i < parsArray.size(); i++) {
                bytes2.write(parsArray.get(i).name + "\n");
            }
            bytes2.close();
        } catch (IOException a) {
            System.out.println(a);
        }

    }

    static void parsing(ArrayList<String> arrays, ArrayList<MyArray> parsArray) {
        for (int i = 0; i < arrays.size(); i++) {
            MyArray temp = new MyArray();
            Pattern patternName = Pattern.compile(" ([^\\[\\]])+\\[");
            Matcher matcher = patternName.matcher(arrays.get(i));
            if (matcher.find()) {
                temp.setName(matcher.group(1));
            }
            Pattern patternType = Pattern.compile("^([a-zA-Z]+) ");
            matcher = patternType.matcher(arrays.get(i));
            if (matcher.find()) {
                if (arrays.get(i).contains("byte")) {
                    temp.setType(1);
                }
                if (arrays.get(i).contains("short")) {
                    temp.setType(2);
                }
                if (arrays.get(i).contains("int")) {
                    temp.setType(4);
                }
                if (arrays.get(i).contains("boolean")) {
                    temp.setType(1);
                }
                if (arrays.get(i).contains("long")) {
                    temp.setType(8);
                }
                if (arrays.get(i).contains("char")) {
                    temp.setType(2);
                }
                if (arrays.get(i).contains("float")) {
                    temp.setType(4);
                }
                if (arrays.get(i).contains("double")) {
                    temp.setType(8);
                }
            }
            Stack<Integer> reverse = new Stack<Integer>();
            Pattern patternSize = Pattern.compile("\\[([0-9]+)\\]");
            matcher = patternSize.matcher(arrays.get(i));
            while (matcher.find()) {
                reverse.push(Integer.valueOf(matcher.group(1)));
            }
            int size = 24 + temp.type * reverse.pop();
            while (!reverse.empty()) {
                size = 24 + size * reverse.pop();
            }
            temp.setSize(size);
            parsArray.add(temp);
        }
    }
}
