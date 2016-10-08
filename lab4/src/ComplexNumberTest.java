public class ComplexNumberTest {
    public static void main(String[] args) {
        ComlexNumber a = new ComlexNumber();
        ComlexNumber b = new ComlexNumber(3.0, 4.0);
        System.out.println(b.printAlgebraicForm());
        System.out.println(b.getExponentialForm());
        System.out.println(b.getTrigonometricForm());
        a = a.subtraction(b);
        System.out.println(a.printAlgebraicForm());
        a.add(b);
        System.out.println(a.printAlgebraicForm());
        a = a.divide(b);
        System.out.println(a.printAlgebraicForm());
        a = a.mulptiple(b);
        System.out.println(a.printAlgebraicForm());
    }
}
