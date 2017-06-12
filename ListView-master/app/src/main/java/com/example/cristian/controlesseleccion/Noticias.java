package com.example.cristian.controlesseleccion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by Cristian on 2017-06-07.
 */
public class Noticias {

    private String idNoticia;
    private String titulo;
    private String subtitulo;
    private String fecha;
    private String categoria;
    private String path;
    private String idCategoria;

    //private String URL;


//    public Noticias(String tit, String sub){
//        titulo = tit;
//        subtitulo = sub;
//    }

    public Noticias(JSONObject a) throws JSONException {
        idNoticia=a.getString("codinoti");
        titulo =  a.getString("titu");
        subtitulo=a.getString("intr");
        fecha=a.get("fechcrea").toString().substring(0,10);
        categoria=a.getString("nomb");
        path=a.getString("path");
        idCategoria=a.getString("cate");

    }

    public String getIdNoticia() {return idNoticia;}

    public String getTitulo(){return titulo;}

    public String getSubtitulo(){return subtitulo;}

    public String getFecha() {return fecha;}

    public String getCategoria() {return categoria;}

    public String getPath() {return path;}

    public String getIdCategoria() {return idCategoria;}

    public static  ArrayList<Noticias> JsonObjectsBuild(JSONArray datos) throws JSONException {

        ArrayList<Noticias> noticias = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            noticias.add(new Noticias(datos.getJSONObject(i)));
        }

        return noticias;

    }



}
