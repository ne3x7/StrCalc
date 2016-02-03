package com.ne3x7.strcalc;

public class Calculator {

    private int index = 0;
    private final String str;
    private boolean wrong = false;

    public Calculator (String expr) {
        this.str = expr;
    }

    public double calculate() {
        return this.expr(str);
    }

    public boolean isOkay() {
        return !wrong;
    }

    private double number(String str) {
        double result = 0.0;
        double div = 10.0;
        boolean negative = false;

        try {
            if (str.charAt(index) == '-') {
                negative = true;
                index++;
            }
        } catch (RuntimeException e) {
            return result;
        }

        try {
            while ((str.charAt(index) >= '0') && (str.charAt(index) <= '9')) {
                result = result * 10.0 + str.charAt(index) - '0';
                index++;
            }
        } catch (RuntimeException e) {
            return result;
        }

        try {
            if (str.charAt(index) == '.') {
                index++;
                while ((str.charAt(index) >= '0') && (str.charAt(index) <= '9')) {
                    result += (str.charAt(index) - '0') / div;
                    div *= 10.0;
                    index++;
                }
            }
        } catch (RuntimeException e) {
            if (negative)
                return -result;
            else
                return result;
        }

        if (negative)
            return -result;
        else
            return result;
    }

    private double expr(String str) {
        double result = this.term(str);

        try {
            while ((str.charAt(index) == '+') || (str.charAt(index) == '-')) {
                switch (str.charAt(index)) {
                    case '+':
                        index++;
                        result += this.term(str);
                        break;
                    case '-':
                        index++;
                        result -= this.term(str);
                        break;
                }
            }
        } catch (RuntimeException e) {
            return result;
        }

        return result;
    }

    private double term(String str) {
        double result = this.factor(str);

        try {
            while ((str.charAt(index) == '*') || (str.charAt(index) == '/')) {
                switch (str.charAt(index)) {
                    case '*':
                        index++;
                        result *= this.factor(str);
                        break;
                    case '/':
                        index++;
                        try {
                            result /= this.factor(str);
                        } catch (Exception e) {
                            wrong = true;
                        }
                        break;
                }
            }
        } catch (RuntimeException e) {
            return result;
        }

        return result;
    }

    private double factor(String str) {
        double result = 0;
        boolean negative = false;

        try {
            if (str.charAt(index) == '-') {
                negative = true;
                index++;
            }
        } catch (RuntimeException e) {
            return result;
        }

        try {
            if (str.charAt(index) == '(') {
                index++;
                result = expr(str);

                if (str.charAt(index) != ')') {
                    wrong = true;
                }

                index++;
            } else
                result = this.number(str);

            if (str.charAt(index) == '^') {
                index++;
                result = Math.pow(result, this.factor(str));
            }
        } catch (RuntimeException e) {
            if (negative)
                return -result;
            else
                return result;
        }

        if (negative)
            return -result;
        else
            return result;
    }
}
