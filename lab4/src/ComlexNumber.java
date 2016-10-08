public class ComlexNumber {
    private double real, imaginary;

    public ComlexNumber(double a, double b) {
        real = a;
        imaginary = b;
    }

    public ComlexNumber() {
        real = 0;
        imaginary = 0;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double getFi() {
        return Math.atan(imaginary / real);
    }

    public double getR() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public String printAlgebraicForm() {
        String temp = new String();
        temp += real;
        if (imaginary > 0) {
            temp += " + " + imaginary + "*i ";
        }
        if (imaginary < 0) {
            temp += " - " + Math.abs(imaginary) + "*i ";
        }
        return temp;
    }

    public String getTrigonometricForm() {
        return (getR() + "(cos(" + getFi() + ") + i*sin(" + getFi() + ")) ");
    }

    public String getExponentialForm() {
        return getR() + "*e^(" + getFi() + "*i) ";
    }

    public ComlexNumber add(ComlexNumber comlexNumber2) {
        ComlexNumber temp = new ComlexNumber(real, imaginary);
        temp.real += comlexNumber2.real;
        temp.imaginary += comlexNumber2.imaginary;
        return temp;
    }

    public ComlexNumber subtraction(ComlexNumber comlexNumber2) {
        ComlexNumber temp = new ComlexNumber(real, imaginary);
        temp.real -= comlexNumber2.real;
        temp.imaginary -= comlexNumber2.imaginary;
        return temp;
    }

    public ComlexNumber mulptiple(ComlexNumber comlexNumber2) {
        ComlexNumber temp = new ComlexNumber();
        temp.real = (real * comlexNumber2.real - imaginary * comlexNumber2.imaginary);
        temp.imaginary = real * comlexNumber2.imaginary + imaginary * comlexNumber2.real;
        return temp;
    }

    public ComlexNumber divide(ComlexNumber comlexNumber2) {
        ComlexNumber temp = new ComlexNumber();
        temp.real = (real * comlexNumber2.real + imaginary * comlexNumber2.imaginary);
        temp.real /= (comlexNumber2.real * comlexNumber2.real + comlexNumber2.imaginary * comlexNumber2.imaginary);
        temp.imaginary = real * comlexNumber2.imaginary - imaginary * comlexNumber2.real;
        temp.imaginary /= (comlexNumber2.real * comlexNumber2.real + comlexNumber2.imaginary * comlexNumber2.imaginary);
        return temp;
    }
}
