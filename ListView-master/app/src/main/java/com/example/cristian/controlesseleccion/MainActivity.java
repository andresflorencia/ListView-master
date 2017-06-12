package com.example.cristian.controlesseleccion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements Asynchtask  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*final String[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);


        Spinner cmbOpciones = (Spinner)findViewById(R.id.cbOpciones);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cmbOpciones.setAdapter(adaptador);

        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
                            TextView txtItem = (TextView)findViewById(R.id.txtItem);
                            txtItem.setText("Seleccionado: " + parent.getItemAtPosition(position));
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                        TextView txtItem = (TextView)findViewById(R.id.txtItem);
                        txtItem.setText("");
                    }
                });*/


       // ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);

//        Noticias[] noticias =
//                new Noticias[]{
//                        new Noticias("Noticia 1", "SubNoticia Contenido  Contenido Contenido Contenido  1"),
//                        new Noticias("Noticia 2", "SubNoticia Contenido  Contenido Contenido Contenido  2"),
//                        new Noticias("Noticia 3", "SubNoticia Contenido  Contenido Contenido Contenido  3"),
//                        new Noticias("Noticia 4", "SubNoticia Contenido  Contenido Contenido Contenido  4")};



        Map<String, String> params = new HashMap<String, String>();

        WebService ws= new WebService("http://186.46.90.102/webservices/wsNews.php", params, MainActivity.this,
                MainActivity.this);
        ws.execute("");
    }


    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray objdataarray= new JSONArray (result);
        ArrayList<Noticias> noticias = Noticias.JsonObjectsBuild(objdataarray);

        AdaptadorNoticias adaptadornoticias = new AdaptadorNoticias(this, noticias);
        ListView lstOpciones = (ListView)findViewById(R.id.lstOpciones);
        lstOpciones.setAdapter(adaptadornoticias);


        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                Intent intent = new Intent(MainActivity.this, verNoticia.class);

                Bundle b = new Bundle();
                b.putString("idNoticia",((Noticias)a.getItemAtPosition(position)).getIdNoticia());
                //b.putString("SubTitulo",((Noticias)a.getItemAtPosition(position)).getSubtitulo());

                intent.putExtras(b);

                startActivity(intent);

            }
        });




    }
}
