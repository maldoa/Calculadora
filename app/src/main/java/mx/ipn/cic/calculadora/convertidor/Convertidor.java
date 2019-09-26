package mx.ipn.cic.calculadora.convertidor;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class Convertidor {
    private Stack stack;
    private StringTokenizer str;
    private String salida = "";
    public Convertidor (String entrada){

        str = new StringTokenizer(entrada);
        stack = new Stack();
    }

    public String transforma() {
        while(str.hasMoreTokens()){
            String token = str.nextToken();
            switch(token) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                case "Sen":
                case "Cos":
                case "Tan":
                case "Sec":
                case "Csc":
                case "Cot":
                case "Ln":
                    operadoresPila(token, 0);
                    break;
                case "(":
                    stack.push(token);
                    break;
                case ")":
                    parentesisIzquiero(token);
                    break;
                case ".":
                    salida = salida + token;
                    break;
                default:
                    salida = salida + " " + token;
                    break;
            }
        }

        while(!stack.empty()){
            salida = salida + " " +stack.pop();
        }

        return salida;
    }
    public void operadoresPila(String operadorActual, int prioridad1){
        while(!stack.empty()) {
            String operadorTop = (String) stack.pop();
            if(operadorTop.equals("(")){
                stack.push(operadorTop);
                break;
            }else{
                int prioridad2;
                if(operadorTop == "+" || operadorTop == "-" || operadorTop == "*"|| operadorTop == "/")
                    prioridad2 = 0;
                else
                    prioridad2 = 1;
                if(prioridad2 < prioridad1) {
                    stack.push(operadorTop);
                    break;
                }else
                    salida = salida + " " + operadorTop;
            }
        }
        stack.push(operadorActual);
    }

    public void parentesisIzquiero(String token){
        while (!stack.empty()) {
            String operador = (String) stack.pop();
            if (operador.equals("("))
                break;
            else
                salida = salida + " " + operador;
        }
    }

    public  double calculator(String[] cadena) {
        Stack<Double> operandos = new Stack<Double>();
        for(String expresion : cadena) {
            if (expresion.trim().equals("")) {
                continue;
            }
            double valor = 0;
            switch (expresion) {
                case "+":
                case "-":
                case "*":
                case "/":
                case "^":
                    double derecha = operandos.pop();
                    double  izquierda = operandos.pop();

                    switch(expresion) {
                        case "+":
                            valor = izquierda + derecha;
                            break;
                        case "-":
                            valor = izquierda - derecha;
                            break;
                        case "*":
                            valor = izquierda * derecha;
                            break;
                        case "/":
                            valor = izquierda / derecha;
                            break;
                        case "^":
                            valor = Math.pow(izquierda, derecha);
                            break;
                        default:
                            break;
                    }
                    operandos.push(valor);
                    break;
                case "Sen":
                case "Cos":
                case "Tan":
                case "Sec":
                case "Csc":
                case "Cot":
                case "Ln":
                    derecha = operandos.pop();
                    switch(expresion) {

                        case "Sen":
                            valor = Math.sin(derecha);
                            break;
                        case "Cos":
                            valor = Math.cos(derecha);
                            break;
                        case "Tan":
                            valor = Math.tan(derecha);
                            break;
                        case "Cot":
                            valor = 1/ Math.tan(derecha);
                            break;
                        case "Csc":
                            valor = 1/ Math.sin(derecha);
                            break;
                        case "Sec":
                            valor = 1 / Math.cos(derecha);
                            break;
                        case "Ln":
                            valor = Math.log(derecha);
                            break;
                        default:
                            break;
                    }
                    operandos.push(valor);
                    break;

                default:
                    operandos.push(Double.parseDouble(expresion));
                    break;
            }
        }
        return operandos.pop();
    }
}