package mx.ipn.cic.calculadora.posfijo;

import java.util.ArrayList;

public class Pila {
    private ArrayList<Object> pila = new ArrayList();

    public Pila(int countTokens) {
    }

    private  void push(Object o){
        pila.add(o);
    }

    private Object pop(){
        if(!(pila.isEmpty())){
            Object o =  pila.get(pila.size() - 1);
            pila.remove(pila.size() - 1);
            return o;
        }
        return null;
    }

/*    public Object pos(){
        if(!(pila.isEmpty())){
            return  pila.get(pila.size() - 1);
        }

        return null;
    }*/



    private Boolean  estado(){
        return pila.isEmpty();
    }

    private void limpiar(){
        pila.clear();
    }
}
