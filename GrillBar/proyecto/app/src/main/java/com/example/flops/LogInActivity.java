package com.example.flops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LogInActivity extends AppCompatActivity {

    //variables necesarias
    public static final String RESOURCE_KEY2 = "ok";
    public TextView etiqueta;
    public EditText usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //inicializo variables
        usuario = findViewById(R.id.entrada_texto_usuario);
        final EditText contraseña = findViewById(R.id.entrada_texto_contrasenya);
        Button acceder = findViewById(R.id.button_acceder);
        etiqueta = findViewById(R.id.etiqueta_validacion);

        etiqueta.setVisibility(View.INVISIBLE);

        //llamo a la bbdd pasandole nombre y código
        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                etiqueta.setVisibility(View.INVISIBLE);
                ejecutarServicio("http://192.168.42.168:80/ordenes/logIn.php", usuario.getText().toString(), contraseña.getText().toString());

            }
        });
    }
    //si la llamada a la bbdd me devuelve un 1 existe el nombre y codigo del empleado
    public void ejecutarServicio(String URL, final String usuario, final String contrasenya){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("1")){
                    Log.d("Response Done", response);
                    etiqueta.setVisibility(View.INVISIBLE);
                    startMainActivity();
                } else {
                    Log.d("Response Error", response);
                    etiqueta.setVisibility(View.VISIBLE);
                }
                Log.d("Response", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                etiqueta.setVisibility(View.VISIBLE);
                Log.e("Volley Error", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> parametros= new HashMap<>();

                parametros.put("usuario", usuario);
                parametros.put("contrasenya", contrasenya);

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    //abro siguiente actividad poniendo al usuario como extra
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(RESOURCE_KEY2, usuario.getText().toString());
        startActivity(intent);
    }
}
