import java.io.*;
import java.util.*;
 
public class Conv {
    private Stack stack;
    private StringTokenizer str;
    private String salida = "";
    public Conv (String entrada){

        str = new StringTokenizer(entrada);
        stack = new Stack();
    }
      
    public String transforma() {
        while(str.hasMoreTokens()){
            char ch = str.nextToken().charAt(0);
            switch(ch) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                    oper(ch, 1);
                    break;
                case '(':
                    stack.push(ch);
                    break;
                case ')':
                    paren(ch);
                    break;
                default:
                    salida = salida + ch;
                    break;
            }
        }
          
        while(!stack.empty()){
            salida = salida + stack.pop();
        }
          
        System.out.println(salida);
        return salida;
    }
    public void oper(char opEsto, int prec1){
        while(!stack.empty()) {
            char opTop = (char) stack.pop();
            if(opTop == '('){
                stack.push(opTop);
                break;
            }else{
                int prec2;
                if(opTop == '+' || opTop == '-' || opTop == '*'|| opTop == '/')
                    prec2 = 1;
                else
                    prec2 = 2;
                if(prec2 < prec1) {
                    stack.push(opTop);
                    break;
                }else
                    salida = salida + opTop;
            }
        }
        stack.push(opEsto);
    }
      
    public void paren(char ch){
        while (!stack.empty()) {
            char chx = (char) stack.pop();
            if (chx == '(')
                break;
            else
                salida = salida + chx;
        }
    }
      
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingresa una expresion infija");
        String entrada = leer.nextLine();
        String salida;
        Conv exp = new Conv(entrada);
        salida = exp.transforma();
        System.out.println("Posfijo es " + salida);
    }
}
