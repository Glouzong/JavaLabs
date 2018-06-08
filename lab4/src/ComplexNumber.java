public class ComplexNumber {
    private double real, imaginary;

    ComplexNumber(double a, double b) {
        real = a;
        imaginary = b;
    }

    private ComplexNumber() {
        real = 0;
        imaginary = 0;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    private double getFi() {
        return Math.atan(imaginary / real);
    }

    private double getR() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    String printAlgebraicForm() {
        String temp = "";
        temp += real;
        if (imaginary > 0) {
            temp += " + " + imaginary + "*i ";
        }
        if (imaginary < 0) {
            temp += " - " + Math.abs(imaginary) + "*i ";
        }
        return temp;
    }

    String getTrigonometricForm() {
        return (getR() + "(cos(" + getFi() + ") + i*sin(" + getFi() + ")) ");
    }

    String getExponentialForm() {
        return getR() + "*e^(" + getFi() + "*i) ";
    }

    ComplexNumber add(ComplexNumber complexNumber2) {
        ComplexNumber temp = new ComplexNumber(real, imaginary);
        temp.real += complexNumber2.real;
        temp.imaginary += complexNumber2.imaginary;
        return temp;
    }

    ComplexNumber subtraction(ComplexNumber complexNumber2) {
        ComplexNumber temp = new ComplexNumber(real, imaginary);
        temp.real -= complexNumber2.real;
        temp.imaginary -= complexNumber2.imaginary;
        return temp;
    }

    ComplexNumber multiple(ComplexNumber complexNumber2) {
        ComplexNumber temp = new ComplexNumber();
        temp.real = (real * complexNumber2.real - imaginary * complexNumber2.imaginary);
        temp.imaginary = real * complexNumber2.imaginary + imaginary * complexNumber2.real;
        return temp;
    }

    ComplexNumber divide(ComplexNumber complexNumber2) {
        ComplexNumber temp = new ComplexNumber();
        temp.real = (real * complexNumber2.real + imaginary * complexNumber2.imaginary);
        temp.real /= (complexNumber2.real * complexNumber2.real + complexNumber2.imaginary * complexNumber2.imaginary);
        temp.imaginary = real * complexNumber2.imaginary - imaginary * complexNumber2.real;
        temp.imaginary /= (complexNumber2.real * complexNumber2.real + complexNumber2.imaginary * complexNumber2.imaginary);
        return temp;
    }
}
