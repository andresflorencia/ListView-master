package com.example.cristian.controlesseleccion;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class verNoticia extends AppCompatActivity implements Asynchtask {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_noticia);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = this.getIntent().getExtras();

        /*TextView txttituloNoticia = (TextView)findViewById(R.id.txtTitulo);
        txttituloNoticia.setText(bundle.getString("Titulo"));*/

        Map<String, String> params = new HashMap<String, String>();
        params.put("id",bundle.getString("idNoticia"));
        WebService ws= new WebService("http://186.46.90.102/webservices/wsNews.php", params, verNoticia.this, verNoticia.this);
        ws.execute("");

    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONArray objdataarray= new JSONArray (result);
        //ArrayList<Noticias> noticias = Noticias.JsonObjectsBuild(objdataarray);

        JSONObject jsonObj= objdataarray.getJSONObject(0);

        //Extrayendo datos del JSON e insertando en cada campo
        TextView txttituloNoticia = (TextView)findViewById(R.id.txtTitulo);
        txttituloNoticia.setText(Html.fromHtml(jsonObj.getString("titu")));

        TextView txtFechaCategoria = (TextView)findViewById(R.id.txtFechaCategoria);
        txtFechaCategoria.setText(jsonObj.getString("fechinic").concat(" | ").concat(jsonObj.getString("nomb")));

        TextView txtIntroduccion = (TextView)findViewById(R.id.txtIntroduccion);
        txtIntroduccion.setText(Html.fromHtml(jsonObj.getString("intr")));

        TextView txtContenido = (TextView)findViewById(R.id.txtcontenido);
        txtContenido.setText(Html.fromHtml(jsonObj.getString("cont")));

        //cargar la imagen para cada noticia
        String URLImg="http://www.uteq.edu.ec/images/noticias/";
        String idCat=jsonObj.getString("cate").concat("/");
        String idNo=jsonObj.getString("codinoti").concat(".jpg");
        ImageView imgNoticia=(ImageView)findViewById(R.id.imgNoti);
        Glide.with(this)
                .load(URLImg.concat(idCat).concat(idNo))
                .into(imgNoticia);
    }
}
