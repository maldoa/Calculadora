package mx.ipn.cic.calculadora.posfijo;

import android.util.Log;

import java.util.StringTokenizer;
import java.util.Stack;

public class Convertidor {


    public String Ejemplo() {

        String entrada = "4 + 5", salida ="";
        StringTokenizer str = new StringTokenizer(entrada);
        Stack pila = new Stack();
        Stack pilaNum = new Stack();

        Log.d("tokens",str.countTokens()+"");
        for (int i = 0; i <= str.countTokens() - 1; i++) {
            String token = str.nextToken();
            Log.d("token",token);
            if (token.trim().contains("+")) {
                String var2 = str.nextToken();
                salida =token+pilaNum.pop().toString()+" "+var2;
                Log.d("suma", salida);
              //  int sum = Integer.parseInt((String) pilaNum.pop()) + Integer.parseInt(var2);
              //  pila.push(Integer.toString(sum));
            } else if (token.trim().contains("*")) {
                int product = Integer.parseInt((String) pila.pop()) * Integer.parseInt((String) pila.pop());
               // pila.push(Integer.toString(product));
            } else {
                pilaNum.push(token);
               // Log.d("num",pilaNum.size()+"");
            }
        }
        return salida;


    }
}
