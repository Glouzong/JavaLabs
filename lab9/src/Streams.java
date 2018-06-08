import java.io.File;
import java.io.IOException;
import java.util.*;

public class Streams {
    public static void main(String[] arg) {
        ArrayList<Pair<String, Long>> tasks = readFile();
        print(tasks);
    }

    private static ArrayList<Pair<String, Long>> readFile() {
        ArrayList<Pair<String, Long>> list = new ArrayList<>();
        Random random = new Random();
        try {
            File input = new File("tasks.in");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                list.add(new Pair<>(scanner.nextLine(), Math.abs(random.nextLong() % 6000)));
            }
            scanner.close();
        } catch (IOException a) {
            System.out.print(a);
        }
        return list;
    }

    private static void checkNewTasks(ArrayList<Pair<String, Long>> list) {
        Random random = new Random();
        try {
            File input = new File("tasks.in");
            Scanner scanner = new Scanner(input);
            int i = 0;
            String temp;
            while (scanner.hasNextLine()) {
                temp = scanner.nextLine();
                i++;
                if (list.size() < i) {
                    list.add(new Pair<>(temp, Math.abs(random.nextLong() % 6000)));
                }
            }
            scanner.close();
        } catch (IOException a) {
            System.out.print(a);
        }
    }

    private static void print(ArrayList<Pair<String, Long>> tasks) {
        ArrayList<String> listPrinter1 = new ArrayList<String>();
        ArrayList<String> listPrinter2 = new ArrayList<String>();
        long endPrinter1 = System.currentTimeMillis();
        long endPrinter2 = System.currentTimeMillis();
        MyThread thread1 = new MyThread("Первый поток");
        MyThread thread2 = new MyThread("Второй поток");
        thread1.start();
        thread2.start();
        int i = 0;
        long checkTime = System.currentTimeMillis() + 20000;
        while (true) {
            while (i < tasks.size()) {
                if (tasks.get(i).getValue() > 5000) {
                    System.out.println("Работа дольше 5 секунд, мы игнорим эту задачу");
                    i++;
                    continue;
                }
                if (thread1.getFlag()) {
                    endPrinter1 = System.currentTimeMillis() + tasks.get(i).getValue();
                    thread1.setTask(tasks.get(i).getKey());
                    thread1.setTime(tasks.get(i).getValue());
                    thread1.setFlag();
                    listPrinter1.add(tasks.get(i).getKey());
                    i++;
                    continue;
                } else {
                    if (thread2.getFlag()) {
                        endPrinter2 = System.currentTimeMillis() + tasks.get(i).getValue();
                        thread2.setTask(tasks.get(i).getKey());
                        thread2.setTime(tasks.get(i).getValue());
                        thread2.setFlag();
                        listPrinter2.add(tasks.get(i).getKey());
                        i++;
                        continue;
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (System.currentTimeMillis() > checkTime) {
                checkNewTasks(tasks);
                checkTime = System.currentTimeMillis() + 10000;
            }
        }
    }
}
