package com.example.flops;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MesaActivity extends AppCompatActivity {

    //variables necesarias
    public int numero_mesa = 0;
    public String atiende = "";
    String temporal ="/";
    String eleccion_salsa = "/";
    public boolean imageCacheReady = false;

    public AdaptadorSide adaptadorSides ;
    public ArrayList<OrdenPlato> platos_ordenados;
    public ArrayList<Side> side_array;
    public ArrayList<ImageCache> imgCache;

    private final int[] platosImgIds = new int[] {
            R.drawable.plato1, R.drawable.plato2, R.drawable.plato3, R.drawable.plato4,
            R.drawable.plato5, R.drawable.plato6, R.drawable.plato7, R.drawable.plato8,
            R.drawable.plato9, R.drawable.plato10, R.drawable.plato11, R.drawable.plato12,
    };
    private int[] sidesImgIds = new int[] {
            R.drawable.side1,
            R.drawable.side2,
            R.drawable.side3,
            R.drawable.side4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa);

        //capturo los datos pasados de la otra actividad
        Intent intent = getIntent();
        numero_mesa = (int)intent.getIntExtra(MainActivity.RESOURCE_KEY, 0);
        atiende = (String)intent.getStringExtra(MainActivity.RESOURCE_KEY2);

        inicializarActividad("http://192.168.42.168:80/ordenes/capturar_img.php");

        //llamo a este método y hasta que los datos no son capturados en totalidad no sigue la ejecución
        //desde esta función llamo al método call_back para seguir su correcto fuuncionamiento
        // mover a CB
    }

    public Bitmap getImageById(int idPlato){
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap tmp = Bitmap.createBitmap(1, 1, conf);
        if (imageCacheReady){
            for(int i = 0; i < imgCache.size(); i++){
                if(imgCache.get(i).getId() == idPlato){
                    tmp = imgCache.get(i).getImg();
                    break;
                }
            }
        }
        return tmp;
    }

    private void imageCacheCallBack(Boolean done){
        if(done){
            capturar_stock("http://192.168.42.168:80/ordenes/captar_stock.php");
        } else {
            //Error
        }
    }
    private void inicializarActividad(String URL){

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                imgCache = new ArrayList<>();

                JSONArray jsonImageList;

                //trato de guardar en jsonStock los valores devueltos por "Response" buscandolo por su nombre "stock"
                try {
                    jsonImageList = response.getJSONArray("imageList");
                    int jsonLength = jsonImageList.length();

                    //recorro el JsonArray y para cada indice extraigo el idProducto y su cantidad
                    for(int i = 0; i < jsonLength; i++){
                        try {
                            JSONObject productImg = jsonImageList.getJSONObject(i);
                            try {
                                int idDb = productImg.getInt("idProducto");
                                String imgB64 = productImg.getString("img");
                                byte[] decodedString = Base64.decode(imgB64, Base64.DEFAULT);
                                Bitmap imgBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                                imgCache.add(new ImageCache(idDb, imgBitMap));


                            } catch (JSONException e) {
                                imageCacheCallBack(false);
                               Log.d("JSON Error 1","JSON incorrect format.");
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            imageCacheCallBack( false);
                            Log.d("JSON Error 2","JSON incorrect format.");
                            e.printStackTrace();
                        }
                    }
                    imageCacheReady = true;
                    //al finalizar el for sigo con la ejecución del programa aportando los datos recogidos

                    imageCacheCallBack(true);
                } catch (JSONException e) {
                    //en caso de error sigo con la ejecución del programa donde Done será falso

                    imageCacheCallBack(false);
                    Log.d("JSON Error 3","JSON incorrect format.");
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ArrayList<Integer>indeicesDB = new ArrayList<>();
                ArrayList<Integer>cantidades_stock = new ArrayList<>();

                //en caso de error sigo con la ejecución del programa donde Done será falso
                imageCacheCallBack(false);

                Log.e("Server Error", error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonRequest);


    }

    private void call_back_stock(ArrayList<Plato> platosDB, Boolean done){

        if(done){
            //inicializo los arrays
            platos_ordenados = new ArrayList<>();
            side_array = new ArrayList<>();

            List<Plato> platos = new ArrayList<>();

            //asigno el número a la mesa
            TextView text_numero_mesa = findViewById(R.id.ventana_numero_mesa);
            text_numero_mesa.setText(String.valueOf(numero_mesa));

            //creación de arrayList de platos
            //String[] nombres = getResources().getStringArray(R.array.nombres_platos);

            //nombre plato y fotos se guardan en Array
            for (int i = 0; i < platosDB.size(); i++){
                Plato plato = platosDB.get(i);
                platos.add(plato);
            }

            //imágenes de guarniciones se guardan en array
            String [] side_nombres = getResources().getStringArray(R.array.side_name);
            for (int i = 0; i < side_nombres.length; i++) {
                Side side = new Side(side_nombres[i], sidesImgIds[i]);
                side_array.add(side);
            }

            //busco el recycler por su id
            RecyclerView recyclerView = findViewById(R.id.recycler_platos);

            // Layout manager para el recyclerView
            RecyclerView.LayoutManager layoutManager =
                    new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);

            // Adaptador platos
            final PlatoAdapter platoAdapter = new PlatoAdapter(platos);
            // Asignar el adaptador al recyclerView
            recyclerView.setAdapter(platoAdapter);

            //le paso el ArrayList
            adaptadorSides = new AdaptadorSide(this, side_array);

            //spinner ver los platos provisionales
            final Spinner spinner_ver_mesa = findViewById(R.id.spinner_ver_mesa);

            //adaptador spinner de platos a ordenar
            final AdaptadorMesa adaptadorMesa = new AdaptadorMesa(this, platos_ordenados);
            spinner_ver_mesa.setAdapter(adaptadorMesa);


            /*spinner_ver_mesa.setSelection(0,false);
            spinner_ver_mesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    Log.d("Spinner listener","onItemSelected");
                    platos_ordenados.remove(position);
                    adaptadorMesa.notifyDataSetChanged();


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    Log.d("Spinner listener","onNothingSelected");

                }
            });*/

            //busco el botón por su id
            Button botton_vaciar_mesa = findViewById(R.id.boton_borrar_mesa);

            //borra todos los platos provisonales añadidos al spinner
            botton_vaciar_mesa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    platos_ordenados.clear();

                    Toast.makeText(MesaActivity.this, "Pedido cancelado", Toast.LENGTH_SHORT).show();
                }
            });

            //botón ejecutar servicio y mandar datos
            Button lanzar_mesa = findViewById(R.id.button_lanzar_mesa);

            //llama al método para ejecutar el servicio y mandar a la bbdd los datos
            lanzar_mesa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //recorro todos los platos
                    for(int i = 0; i < platos_ordenados.size(); i++){
                        ejecutarServicioWeb("http://192.168.42.168:80/ordenes/insertar_producto.php", platos_ordenados.get(i).getNombre(),
                                platos_ordenados.get(i).getButter(),platos_ordenados.get(i).getPunto(),platos_ordenados.get(i).getSide(), atiende);
                    }
                    Toast.makeText(MesaActivity.this, "CORRECTO", Toast.LENGTH_SHORT).show();
                    platos_ordenados.clear();
                }
            });
        } else {
            //en caso de que Done sea valor false
            Log.d("JSON Error 4","JSON incorrect format.");
            //tirar error
        }
    }

    //le asigna valores al array de cantidades
    //realizo un JsonRequest a través de webServices
    public void capturar_stock(String URL){

        final ArrayList<Plato> platos = new ArrayList<>();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){

                JSONArray jsonStock;
                //trato de guardar en jsonStock los valores devueltos por "Response" buscandolo por su nombre "stock"
                try {
                    jsonStock = response.getJSONArray("productList");
                    int jsonLength = jsonStock.length();

                    for(int i = 0; i < jsonLength; i++){
                        try {
                            JSONObject product = jsonStock.getJSONObject(i);
                            try {
                                platos.add(new Plato(product.getInt("idProducto"), product.getString("nombre"), product.getInt("cantidad")));
                                int cantidad =  product.getInt("cantidad");
                                int idDb = product.getInt("idProducto");

                            } catch (JSONException e) {
                                //en caso de error sigo con la ejecución del programa donde Done será falso
                                call_back_stock(platos, false);
                                Log.d("JSON Error 1","JSON incorrect format.");
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            //en caso de error sigo con la ejecución del programa donde Done será falso
                            call_back_stock(platos, false);
                            Log.d("JSON Error 2","JSON incorrect format.");
                            e.printStackTrace();
                        }
                    }
                    //al finalizar el for sigo con la ejecución del programa aportando los datos recogidos
                    call_back_stock(platos, true);
                } catch (JSONException e) {
                    //en caso de error sigo con la ejecución del programa donde Done será falso
                    call_back_stock(platos, false);
                    Log.d("JSON Error 3","JSON incorrect format.");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ArrayList<Integer>indeicesDB = new ArrayList<>();
                ArrayList<Integer>cantidades_stock = new ArrayList<>();

                //en caso de error sigo con la ejecución del programa donde Done será falso
                call_back_stock(platos, false);

                Log.e("Server Error", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);
    }

    //todos lo datos recogidos con la aplicación se mandan a la bbdd
    private void ejecutarServicioWeb(String URL, final String nombre, final String butter, final String punto, final String side, final String atiende){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response", response);

                //capturo stock existente para cada ingrediente
                capturar_stock("http://192.168.42.168:80/ordenes/captar_stock.php");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());

                Toast.makeText(MesaActivity.this, "TODO Mal: ", Toast.LENGTH_SHORT).show();
            }
        }) {
            //en parametros se agregan los datos
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> parametros= new HashMap<>();

                parametros.put("nombre", nombre);
                parametros.put("salsa", butter);
                parametros.put("punto", punto);
                parametros.put("side", side);
                parametros.put("atiende", atiende);
                parametros.put("numero", String.valueOf(numero_mesa));

                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    //adaptador RecyclerView platos
    class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoViewHolder> {

        private List<Plato> platos;

        public PlatoAdapter(List<Plato> platos) {
            this.platos = platos;
        }

        @NonNull
        @Override
        public PlatoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View platoView = LayoutInflater.from(parent.getContext()).inflate(R.layout.plato_card,
                    parent, false);
            PlatoViewHolder platoViewHolder = new PlatoViewHolder(platoView);

            return platoViewHolder;
        }

        @SuppressLint("ResourceType")
        @Override
        public void onBindViewHolder(@NonNull final PlatoViewHolder holder, int position) {

            //asigno valores al holder
            Plato plato = platos.get(position);
            holder.setPlato(plato);
           // holder.platoImage.setImageResource(plato.getPicture());
            holder.platoImage.setImageBitmap(getImageById(plato.getIdPlato()));
            holder.textPlatoName.setText(plato.getNombre());
            holder.cantidad.setText(String.valueOf(plato.getCantidad()));

            //necesito 5 indices de seekbar
            holder.sickbar.setMax(4);

            //invoco al setOnSeekBarChangeListener de los seekbars
            holder.sickbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    updateProgress(progress);
                }

                //progreso del seekbar actualizando punto y foto sugestiva punto carne
                //ifs o un switch
                private void updateProgress(int progress) {
                    if(progress == 0) {
                        holder.punto_carne.setText("AZUL");
                        holder.color_coccion.setImageResource(R.drawable.carne1);
                    }else if(progress == 1){
                        holder.punto_carne.setText("POCO HECHO");
                        holder.color_coccion.setImageResource(R.drawable.carne2);
                    }else if(progress == 2){
                        holder.punto_carne.setText("AL PUNTO");
                        holder.color_coccion.setImageResource(R.drawable.carne3);
                    }else if(progress == 3){
                        holder.punto_carne.setText("HECHO");
                        holder.color_coccion.setImageResource(R.drawable.carne4);
                    }else {
                        holder.punto_carne.setText("MUY HECHO");
                        holder.color_coccion.setImageResource(R.drawable.carne5);
                    }
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            //boton que abre la ventana Dialog en caso de que la cantidad del plato no esté a 0
            holder.boton_add_platico.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!(Integer.parseInt(holder.cantidad.getText().toString()) < 1)){

                        //declaro el AlertDialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(MesaActivity.this);
                        View view = getLayoutInflater().inflate(R.layout.dialogo_cuadro, null);
                        builder.setTitle("Completa tu plato");

                        //variables identificadas
                        final ImageView garlic_pic = view.findViewById(R.id.image_garlic);
                        final ImageView onion_pic = view.findViewById(R.id.image_onion);
                        final Spinner spinnerSide = view.findViewById(R.id.spinner_side);
                        final TextView texto_salsa = view.findViewById(R.id.texto_eleccion_salsa);

                        //realizo un onClickListener a 2 fotos para determinar que tipo de salsa llevará
                        garlic_pic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eleccion_salsa = "garlic ";
                                texto_salsa.setText("Garlic");

                            }
                        });
                        onion_pic.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eleccion_salsa = "onion";
                                texto_salsa.setText("Onion");
                            }
                        });

                        //adaptador guarniciones
                        adaptadorSides = new AdaptadorSide(MesaActivity.this, side_array);

                        spinnerSide.setAdapter(adaptadorSides);

                        //guardo en temporal la elección del spinner
                        spinnerSide.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch(position){
                                    case 0:
                                        temporal = "Vegetables";
                                        break;
                                    case 1:
                                        temporal = "Fries";
                                        break;
                                    case 2:
                                        temporal = "Mash Potatoes";
                                        break;
                                    case 3:
                                        temporal = "Salad";
                                        break;
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        //boton positivo guarda las elecciones
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //se guarda en platos la información
                                platos_ordenados.add(new OrdenPlato((String) holder.textPlatoName.getText(), eleccion_salsa, (String) holder.punto_carne.getText(), temporal));
                                Toast.makeText(MesaActivity.this, "Añadido a Pedido", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                        //boton negativo cierra dialog
                        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.setView(view);
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } else {
                        //en caso de agotado muestra Toast
                        Toast.makeText(MesaActivity.this, "Producto agotado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return platos.size();
        }

        class PlatoViewHolder extends RecyclerView.ViewHolder {

            //declaraciones de objetos y variables necesarias
            private ImageView platoImage;
            private TextView textPlatoName;
            private SeekBar sickbar;
            private TextView punto_carne;
            private ImageView color_coccion;
            private Button boton_add_platico;
            private TextView cantidad ;
            private ConstraintLayout linearLayout;
            private Plato plato;

            public PlatoViewHolder(@NonNull View itemView) {
                super(itemView);

                //identifico por su id a los elementos gráficos
                platoImage = itemView.findViewById(R.id.image_plato);
                textPlatoName = itemView.findViewById(R.id.text_plato_name);
                sickbar = itemView.findViewById(R.id.seekBar_punto_carne);
                punto_carne = itemView.findViewById(R.id.punto_carne);
                color_coccion = itemView.findViewById(R.id.imagen_coccion_carne);
                cantidad = itemView.findViewById(R.id.textView_cantidad_stock);
                boton_add_platico = itemView.findViewById(R.id.boton_add_plato);
                linearLayout = itemView.findViewById(R.id.constraintlayout);
            }

            public void setPlato(Plato plato) {
                this.plato = plato;
            }
        }
    }

    //adaptador Spinner guarniciones
    //es un spinner normal que recibe un ArrayList e imprime su foto y nombre
    public class AdaptadorSide extends ArrayAdapter<Side> {

        public AdaptadorSide(@NonNull Context context, @NonNull List<Side> objects) {
            super(context, R.layout.side_card, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getDropDownView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            Side side = getItem(position);

            View view;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.side_card, null);
            } else {
                view = convertView;
            }
            ImageView imageSide = view.findViewById(R.id.image_side);
            TextView textNombre = view.findViewById(R.id.text_side);
            imageSide.setImageResource(side.getImageResId());
            textNombre.setText(side.getName());

            return view;
        }
    }

    //adaptador Spinner vista previa platos pedidos
    //imprime en formato texto
    //con un click en la posición del plato se elimina ese elemento
    class AdaptadorMesa extends ArrayAdapter<OrdenPlato> {

        public List<OrdenPlato> platos = new ArrayList<>();


        public AdaptadorMesa(@NonNull Context context, @NonNull List<OrdenPlato> objects) {
            super(context, R.layout.mesa_layout, objects);
            platos = objects;


        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getDropDownView(position, convertView, parent);
        }


        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            OrdenPlato ordenado = getItem(position);

            View view;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.mesa_layout, null);
            } else {
                view = convertView;
            }

            TextView textNombre = view.findViewById(R.id.text_nombre_plato);
            TextView textButter = view.findViewById(R.id.texto_butter);
            TextView textPunto = view.findViewById(R.id.texto_punto);
            TextView idSide = view.findViewById(R.id.texto_side);

            textNombre.setText(ordenado.getNombre());
            textButter.setText(ordenado.getButter());
            textPunto.setText(ordenado.getPunto());
            idSide.setText(ordenado.getSide());

            return view;
        }


    }
}
