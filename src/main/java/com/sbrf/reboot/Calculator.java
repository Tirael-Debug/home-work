package com.sbrf.reboot;

public class Calculator {

    public Calculator() {
    }

    public int getAddition(int a, int b) {
        return a + b;
    };

    public int getSubtraction(int a, int b) {
        return  a - b;
    }

    public int getMultiplication(int a, int b) {
        return a * b;
    }

    public int getDivision(int a, int b) {
        return a / b;
    }

    public double getSqrt(int a) {
        return Math.sqrt(a);
    }

    public int getFactorial(int a) {
        int result = 1;
        for (int i = 1; i <= a; i++) {
            result = result * i;
        }
        return result;
    }

    public double getPow(double a, double pow) {
        return Math.pow(a, pow);
    }
}
