package com.example.graph;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InvioDati extends AsyncTask<String,Void,String> {
    private Context context;


    public InvioDati(Context context){
        this.context = context;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String ... url) {
        // azioni di invio
        URL paginaURL = null;
        InputStream risposta = null;
        try {
            paginaURL = new URL(url[0]);
            HttpURLConnection client = (HttpURLConnection) paginaURL.openConnection();
            risposta = new BufferedInputStream(client.getInputStream());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mostroDati(risposta);


    }
    @Override
    protected void onPostExecute(String result) {
        String ce = "";
        try {
            JSONObject jObject = new JSONObject(result);
            ce = jObject.getString("ce");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(ce.equals("SI")) {
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "l'utente non è presente", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String mostroDati(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
