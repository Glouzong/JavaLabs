import java.util.Scanner;

import static java.lang.Math.*;

public class Calculator {

    public static void main(String[] args) {
        System.out.println(" Hello World! Enter an expression \nexample: abs -10\n         10 + 11,1" +
                "\n________________________________");
        Scanner scan = new Scanner(System.in);
        showColculator(scan);
    }

    static void showColculator(Scanner scan) {
        String operatorUnary = "abs sqrt cos sin tg lg ln";// and "+ - / * % ^"
        boolean exit = true;
        while (exit) {
            String operator = scan.next();
            if (operator.contains("exit")) {
                exit = false;
                break;
            }
            try {
                if (operatorUnary.indexOf(operator) == -1) {
                    enterBinaryOperator(scan, operator);
                } else {
                    enterUnaryOperator(scan, operator);
                }
            } catch (Exception t) {
                System.out.println("Entered an invalid expression!");
            }
        }
    }

    static void enterUnaryOperator(Scanner scan, String operator) throws Exception {
        double number1 = scan.nextDouble();
        switch (operator) {
            case "abs":
                System.out.println(abs(number1));
                break;
            case "sqrt":
                System.out.println(sqrt(number1));
                break;
            case "cos":
                System.out.println(cos(number1));
                break;
            case "sin":
                System.out.println(sin(number1));
                break;
            case "tg":
                System.out.println(tan(number1));
                break;
            case "lg":
                System.out.println(log10(number1));
                break;
            case "ln":
                System.out.println(log(number1));
                break;
        }
        System.out.println("________________________________");
    }

    static void enterBinaryOperator(Scanner scan, String operator) throws Exception {
        double number1 = Double.parseDouble(operator);
        operator = new String(scan.next());
        String operators = "abs sqrt cos sin tg lg ln + - / * % ^";
        if (!operators.contains(operator)) {
            throw new Exception();
        }
        double number2 = scan.nextDouble();
        switch (operator) {
            case "+":
                System.out.println(" = " + (number1 + number2));
                break;
            case "-":
                System.out.println(" = " + (number1 - number2));
                break;
            case "/":
                System.out.println(" = " + (number1 / number2));
                break;
            case "*":
                System.out.println(" = " + (number1 * number2));
                break;
            case "%":
                System.out.println(" = " + (number1 % number2));
                break;
            case "^":
                System.out.println(" = " + pow(number1, number2));
                break;
        }
        System.out.println("________________________________");
    }


}
