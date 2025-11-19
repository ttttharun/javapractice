package com.javapractice;

@FunctionalInterface
interface IMathFunction {
    int calculate(int a, int b);
    static void printResult(int a, int b, String function, IMathFunction fobj) {
        System.out.println("Result of: "+function+ " function is " + fobj.calculate(a, b));
    }
}

public class MathOperationApp {
    public static void main(String[] args) {
        IMathFunction add = Integer::sum;
        IMathFunction multiply = (a, b) -> a*b;
        IMathFunction division = (a, b) -> a/b;

        System.out.println("Addition: " + add.calculate(1,1));
        System.out.println("Multiply: " + multiply.calculate(1,7));
        System.out.println("Division: " + division.calculate(1,1));

        IMathFunction.printResult(7, 8, "Addition", add);
        IMathFunction.printResult(7, 8, "Multiplication", multiply);
        IMathFunction.printResult(17, 8, "Division", division);
    }
}
