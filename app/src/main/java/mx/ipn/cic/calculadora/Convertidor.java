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
            String ch = str.nextToken();
            System.out.println(ch);
            switch(ch) {
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
                    oper(ch, 1);
                    break;
                case "(":
                    stack.push(ch);
                    break;
                case ")":
                    paren(ch);
                    break;
                case ".":
                    salida = salida + ch;
                    break;
                default:
                    salida = salida + " " + ch;
                    break;
            }
        }
          
        while(!stack.empty()){
            salida = salida + " " +stack.pop();
        }
          
        System.out.println(salida);
        return salida;
    }
    public void oper(String opEsto, int prec1){
        while(!stack.empty()) {
            String opTop = (String) stack.pop();
            if(opTop.equals("(")){
                stack.push(opTop);
                break;
            }else{
                int prec2;
                if(opTop == "+" || opTop == "-" || opTop == "*"|| opTop == "/")
                    prec2 = 1;
                else
                    prec2 = 2;
                if(prec2 < prec1) {
                    stack.push(opTop);
                    break;
                }else
                    salida = salida + " " + opTop;
            }
        }
        stack.push(opEsto);
    }
      
    public void paren(String ch){
        while (!stack.empty()) {
            String chx = (String) stack.pop();
            if (chx.equals("("))
                break;
            else
                salida = salida + " " + chx;
        }
    }
      
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingresa una expresion infija");
        String entrada = leer.nextLine();
        String salida;
        Convertidor exp = new Convertidor(entrada);
        salida = exp.transforma();
        System.out.println("Posfijo es " + salida);
        String ejemplo = "1.87 3.654 + 4 * Sen";
        String[] strArr = salida.split(" ");

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
