import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternLabs {
    public static void main(String[] args) {
        String string;
        Scanner scan = new Scanner(System.in);
        string = scan.nextLine();
        //System.out.println(italics(string));
        //System.out.println(itIsTime(string));
        //System.out.println(itIsGUID(string));
        //System.out.println(itIsIPv4(string));
        //System.out.println(itIsURL(string));
        //System.out.println(itIsData(string));
        //System.out.println(itIsChrome(string));
        //takeToken(string);
        //System.out.println(itIsIRC(string));
    }

    public static String italics(String string) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\*[^*]+\\*");
        String buffer = " " + string + " ";
        Matcher matcher = pattern.matcher(buffer);
        String result = "";
        int begin = 1;
        while (matcher.find()) {
            String string1 = buffer.substring(matcher.start() - 1, matcher.start());
            String string2 = buffer.substring(matcher.end() - 1, matcher.end());
            if (!((string1.equals("*")) && (string2.equals("*")))) {
                result += buffer.substring(begin, matcher.start()) + "<em>";
                result += buffer.substring(matcher.start() + 1, matcher.end() - 1);
                result += "</em>";
                begin = matcher.end();
            }
        }
        result += buffer.substring(begin, buffer.length() - 1);
        return result;
    }

    public static boolean itIsTime(String string) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-2][0-4]:[0-5][0-9]$");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static boolean itIsGUID(String string) {
        java.util.regex.Pattern pattern1 = java.util.regex.Pattern.compile("^\\{([0-9a-fA-F]){8}\\-([0-9a-fA-F]){4}" +
                "\\-([0-9a-fA-F]){4}\\-([0-9a-fA-F]){4}\\-([0-9,a-fA-F]){12}\\}$");
        java.util.regex.Pattern pattern2 = java.util.regex.Pattern.compile("^([0-9a-fA-F]){8}\\-([0-9a-fA-F]){4}" +
                "\\-([0-9a-fA-F]){4}\\-([0-9a-fA-F]){4}\\-([0-9,a-fA-F]){12}$");
        Matcher matcher1 = pattern1.matcher(string);
        Matcher matcher2 = pattern2.matcher(string);
        return (matcher1.find() || matcher2.find());
    }

    public static boolean itIsIPv4(String string) {
        Pattern pattern1 = Pattern.compile("^([0-9]{1,3})\\.([0-9]{1,3})\\." +
                "([0-9]{1,3})\\.([0-9]{1,3})$");
        Pattern pattern2 = Pattern.compile("^([0-9]{1,10})$");
        Pattern pattern3 = Pattern.compile("^([0-7]{1,11})$");
        Pattern pattern4 = Pattern.compile("^([0-9a-fA-F]{1,8})$");
        Pattern pattern5 = Pattern.compile("^(0x[0-9a-fA-F]{2}\\.){3}0x[0-9a-fA-F]{2}$");
        Pattern pattern6 = Pattern.compile("^(0[0-4][0-7]{2}\\.){3}0[0-4][0-7]{2}$");
        Matcher matcher = pattern1.matcher(string);
        if (matcher.find()) {
            for (int i = 1; i <= 4; i++) {
                if (Integer.parseInt(matcher.group(i)) > 255) {
                    return false;
                }
            }
            return true;
        }
        matcher = pattern2.matcher(string);
        if (matcher.find()) {
            if ((matcher.group(1).length() == 10) && (matcher.group(1).compareTo("4294967296") > -1)) {
                return false;
            }
            return true;
        }
        matcher = pattern3.matcher(string);
        if (matcher.find()) {
            if ((matcher.group(1).length() == 11) && (matcher.group(1).compareTo("40000000000") > -1)) {
                return false;
            }
            return true;
        }
        matcher = pattern4.matcher(string);
        if (matcher.find()) {
            return true;
        }
        matcher = pattern5.matcher(string);
        if (matcher.find()) {
            return true;
        }
        matcher = pattern6.matcher(string);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean itIsURL(String string) {
        Pattern pattern = Pattern.compile("(https?://)?(([\\w\\-]{2,}(?<!-)\\.)*\\w{2,})(:\\d{4})?(/(\\w+/)*\\w+(\\.\\w+)?)?(\\?(\\w+=\\w+[&;])*\\w+=\\w+)?([%#]\\w+)?");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    public static boolean itIsBigYear(int temp) {
        if (temp % 400 == 0) {
            return true;
        }
        if (temp % 100 == 0) {
            return false;
        }
        if (temp % 4 == 0) {
            return true;
        }
        return false;
    }

    public static boolean itIsData(String string) {
        Pattern pattern = Pattern.compile("^(([0-2][0-9])|(3[0-1]))/((1[0-2])|(0[0-9]))/((1[6-9][0-9]{2})|([2-9][0-9]{3}))");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            int temp = Integer.parseInt(string.substring(matcher.end() - 4, matcher.end()));
            if (itIsBigYear(temp)) {
                temp = Integer.parseInt(string.substring(3, 5));
                if (temp == 2) {
                    temp = Integer.parseInt(string.substring(0, 2));
                    if (temp > 29) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            temp = Integer.parseInt(string.substring(3, 5));
            int tempDay = Integer.parseInt(string.substring(0, 2));
            temp = 28 + (temp + temp / 8) % 2 + 2 % temp + 2 / temp;
            if (tempDay > temp) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean itIsChrome(String string) {
        Pattern pattern = Pattern.compile("^#[0-9a-fA-F]{6}$");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static void takeToken(String string) {
        Pattern pattern = Pattern.compile("(\".+\")|([\\w\\d]+-[\\w\\d]+)|([\\w\\d]+'[\\w\\d]+)|([\\w\\d]+)");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            if (matcher.group(1) != null) {
                String ministring = string.substring(matcher.start() + 1, matcher.end() - 1);
                Matcher minimatcher = pattern.matcher(ministring);
                if (minimatcher.find()) {
                    System.out.print(minimatcher.group());
                }
                while (minimatcher.find()) {
                    System.out.print(" " + minimatcher.group());
                }
            } else {
                System.out.print(matcher.group());
            }
        }
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                String ministring = string.substring(matcher.start() + 1, matcher.end() - 1);
                Matcher minimatcher = pattern.matcher(ministring);
                if (minimatcher.find()) {
                    System.out.print("," + minimatcher.group());
                }
                while (minimatcher.find()) {
                    System.out.print(" " + minimatcher.group());
                }
            } else {
                System.out.print("," + matcher.group());
            }
        }
        System.out.println();
    }

    public static boolean itIsIRC(String string) {
        /*String url="((https?://)?(([\\w\\-]{2,}(?<!-)\\.)*\\w{2,})(:\\d{4})?(/(\\w+/)*\\w+(\\.\\w+)?)?(\\?(\\w+=\\w+[&;])*\\w+=\\w+)?([%#]\\w+)?)";
        String nick="(\\w[\\w\\d\\-\\[\\]\\\\\\`\\^\\{\\}]*)";*/
        Pattern pattern = Pattern.compile("(\\[\\:(((https?://)?(([\\w\\-]{2,}(?<!-)\\.)*\\w{2,})(:\\d{4})?(/(\\w+/)*\\w+(\\.\\w+)?)?(\\?(\\w+=\\w+[&;])*\\w+=\\w+)?([%#]\\w+)?)|(\\w[\\w\\d\\-\\[\\]\\\\\\`\\^\\{\\}]*))(\\![\\S]+)?(\\@url)? +\\])?(([a-zA-Z]+)|(\\d{3,3})) +(\\[\\:\\S*\\])?$");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}