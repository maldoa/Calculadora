package mx.ipn.cic.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;
import mx.ipn.cic.calculadora.convertidor.Convertidor;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Convertidor conv;
    TextView tv;
    String texto = "", operacion = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView)findViewById(R.id.textoCalculadora);

    }

    @Override
    public void onClick(View view) {
       // Log.d("Acción",((Button)view).getText().toString());


        operacion = ((Button)view).getText().toString();
        if(operacion.equals("+/-"))
            operacion = "-";
        if (operacion.substring(operacion.length() - 1).equals("."))
            operacion = operacion;
        else
            if (!isNumeric(operacion.substring(operacion.length() - 1)))
                operacion = " "+operacion+" ";

        texto += operacion;
        if(((Button)view).getText().toString().equals("C"))
            texto = "";
        if(((Button)view).getText().toString().equals("=")) {
            texto = texto.substring(0,texto.length()-2);
            conv = new Convertidor(texto);
           // Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();
            texto = conv.transforma();
            Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();
            String[] strArr = texto.split(" ");
            texto = " "  + conv.calculator(strArr);
        }
        tv.setText(texto.replace(" ",""));

        Log.d("Acción",texto.replace(" ",""));

            //else
        //Toast.makeText(getApplicationContext(),((Button)view).getText().toString(),Toast.LENGTH_LONG).show();
        //Log.d("resu", conv.Ejemplo());



    }

    public  boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
