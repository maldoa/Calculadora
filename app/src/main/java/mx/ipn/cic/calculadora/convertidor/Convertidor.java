import java.io.*;
import java.util.*;
 
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
                    operador(token, 1);
                    break;
                case "(":
                    stack.push(token);
                    break;
                case ")":
                    parentesis(token);
                    break;
                default:
                    salida = salida + " " + token;
                    break;
            }
        }
          
        while(!stack.empty()){
            salida = salida + " " + stack.pop();
        }
          
        return salida;
    }

    public void operador(String token, int valor1){
        while(!stack.empty()) {
            token =(String) stack.pop();
            if(token == "("){
                stack.push(token);
                break;
            }else{
                int valor2;
                if(token == "+" || token == "-" || token == "*" || token == "/")
                    valor2 = 1;
                else
                    valor2 = 2;
                if(valor2 < valor1) {
                    stack.push(token);
                    break;
                }else
                    salida = salida + " " + token;
            }
        }
        stack.push(token);
    }
      
    public void parentesis(String token){
        while (!stack.empty()) {
            token = (String) stack.pop();
            if (token == "(")
                break;
            else{
                salida = salida + token+ " )";
                }
        }
    }
      
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingresa una expresion infija");
        Convertidor exp = new Convertidor(leer.nextLine());
        System.out.println("Posfijo es " + exp.transforma());
    }
}
