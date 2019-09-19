package mx.ipn.cic.calculadora.convertidor;

import java.io.*;
import java.util.*;
import java.lang.Math;


class Evaluador {
    public static void main(String[] args) {
        String ejemplo = "1.87 3.654 + 4 * Sen";
        String[] strArr = ejemplo.split(" ");

        System.out.println(calculator(strArr));
    }

    public static double calculator(String[] strArr) {
        Stack<Double> operands = new Stack<Double>();

        for(String str : strArr) {
            if (str.trim().equals("")) {
                continue;
            }

            switch (str) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                    double right = operands.pop();
                    double left = operands.pop();
                    double value = 0;
                    switch(str) {
                        case "+":
                            value = left + right;
                            break;
                        case "-":
                            value = left - right;
                            break;
                        case "*":
                            value = left * right;
                            break;
                        case "/":
                            value = left / right;
                            break;
                        case "^":
                            value = Math.pow(left, right);
                            break;
                        default:
                            break;
                    }
                    operands.push(value);
                    break;
                case "Sen":
                case "Cos":
                case "Tan":
                case "Sec":
                case "Csc":
                case "Cot":
                case "Ln":
                    right = operands.pop();
                    value = 0;
                    switch(str) {

                        case "Sen":
                            value = Math.sin(right);
                            break;
                        case "Cos":
                            value = Math.cos(right);
                            break;
                        case "Tan":
                            value = Math.tan(right);
                            break;
                        case "Cot":
                            value = 1/ Math.tan(right);
                            break;
                        case "Csc":
                            value = 1/ Math.sin(right);
                            break;
                        case "Sec":
                            value = 1 / Math.cos(right);
                            break;
                        case "Ln":
                            value = Math.log(right);
                            break;
                        default:
                            break;
                    }
                    operands.push(value);
                    break;

                default:
                    operands.push(Double.parseDouble(str));
                    break;
            }
        }
        return operands.pop();
    }
}