import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAndStream {
    public static void main(String[] args) {
        String directory = readDirectory();
        LinkedList<String> ways = writeDirectory(directory);
        createDirectory(directory, ways);
        String separator = File.separator;
        System.out.print(separator);
    }

    private static String readDirectory() {
        String wayDirectory = "";
        String directory;
        try (FileReader reader = new FileReader("root.txt")) {
            Scanner scanner = new Scanner(reader);
            wayDirectory = scanner.nextLine();
            directory = scanner.nextLine();
            wayDirectory = wayDirectory + File.separator + directory;
            File path = new File(wayDirectory);
            if (!path.exists()) {
                path.mkdir();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return wayDirectory;
    }

    private static LinkedList<String> writeDirectory(String beginDirectory) {
        LinkedList<String> ways = new LinkedList<String>();
        try (FileReader reader = new FileReader("files.txt")) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                ways.add(scanner.nextLine());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        ways.sort(Comparator.reverseOrder());
        return ways;
    }

    private static void createDirectory(String beginDirectory, LinkedList<String> ways) {
        LinkedList<String> poem = new LinkedList<String>();
        try (FileReader reader = new FileReader("poem.txt")) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                poem.add(scanner.nextLine());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        for (String way1 : ways) {
            Pattern pattern1 = Pattern.compile("((" +
                    File.separator + "[^" +
                    File.separator + "]+)+)" +
                    File.separator + "[^" +
                    File.separator + "]*");
            Matcher matcher1 = pattern1.matcher(way1);
            String way = beginDirectory;
            if (matcher1.find()) {
                way += matcher1.group(1);
            }

            int level = -1;
            Pattern pattern2 = Pattern.compile(File.separator + "[^" +
                    File.separator + "]+");
            Matcher matcher2 = pattern2.matcher(way1);
            while (matcher2.find()) {
                level++;
            }

            Pattern pattern3 = Pattern.compile("(" +
                    File.separator + "[^" +
                    File.separator + "]*$)");
            Matcher matcher3 = pattern3.matcher(way1);
            matcher3.find();
            File dr = new File(way);
            dr.mkdirs();
            way += matcher3.group(0);
            dr = new File(way);
            try {
                if (!matcher3.group(0).contains(".")) {
                    dr = new File(way);
                    dr.mkdirs();
                    continue;
                }
                if (!dr.exists()) {
                    dr.createNewFile();
                    FileWriter writer = new FileWriter(dr);
                    writer.write(way + "\n" + level + "\n" + poem.get(level % poem.size()) + "\n");
                    writer.flush();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
