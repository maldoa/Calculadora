package mx.ipn.cic.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import mx.ipn.cic.calculadora.posfijo.Convertidor;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Convertidor conv = new Convertidor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        Log.d("Acción",((Button)view).getText().toString());
        Toast.makeText(getApplicationContext(),((Button)view).getText().toString(),Toast.LENGTH_LONG).show();
        Log.d("resu", conv.Ejemplo());
    }
}
