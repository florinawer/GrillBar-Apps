package com.example.flops;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String RESOURCE_KEY = "ok";
    public static final String RESOURCE_KEY2 = "oks";
    public int numero_mesa = 0;
    public String atiende;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //capturo el nombre del usuario de la actividad anterior
        Intent intent = getIntent();
        atiende = intent.getStringExtra(LogInActivity.RESOURCE_KEY2);

        //imagenes de las mesas disponibles
        ImageView mesa1 = findViewById(R.id.imagen_mesa_1);
        ImageView mesa2 = findViewById(R.id.imagen_mesa_2);
        ImageView mesa3 = findViewById(R.id.imagen_mesa_3);
        ImageView mesa4 = findViewById(R.id.imagen_mesa_4);
        ImageView mesa5 = findViewById(R.id.imagen_mesa_5);
        ImageView mesa6 = findViewById(R.id.imagen_mesa_6);
        ImageView mesa7 = findViewById(R.id.imagen_mesa_7);
        ImageView mesa8 = findViewById(R.id.imagen_mesa_8);
        ImageView mesa9 = findViewById(R.id.imagen_mesa_9);

        //identifico que mesa se pulsa
        mesa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 1;
                startMesaActivity();
            }
        });
        mesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 2;
                startMesaActivity();
            }
        });
        mesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 3;
                startMesaActivity();
            }
        });
        mesa4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 4;
                startMesaActivity();
            }
        });
        mesa5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 5;
                startMesaActivity();
            }
        });
        mesa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 6;
                startMesaActivity();
            }
        });
        mesa6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 6;
                startMesaActivity();
            }
        });
        mesa7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 7;
                startMesaActivity();
            }
        });
        mesa8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 8;
                startMesaActivity();
            }
        });
        mesa9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero_mesa = 9;
                startMesaActivity();
            }
        });
    }

    //pongo en el intento el numero de mesa y la persona que atiende
    private void startMesaActivity() {
        Intent intent = new Intent(this, MesaActivity.class);
        intent.putExtra(RESOURCE_KEY, numero_mesa);
        intent.putExtra(RESOURCE_KEY2, atiende);

        startActivity(intent);
    }
}
