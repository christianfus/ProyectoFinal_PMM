package com.example.christian.proyectofin_pmm;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Coordenadas extends Fragment {
    private static final String TAG = "Http Connection";
    private EditText longitud;
    private EditText latitud;
    private Button boton;
    private TextView texto;
    private ArrayAdapter arrayAdapter = null;
    private String direccion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.coordenadas, container, false);

        longitud = (EditText)RootView.findViewById(R.id.longitud);
        latitud = (EditText)RootView.findViewById(R.id.latitud);
        boton = (Button)RootView.findViewById(R.id.boton);
        texto = (TextView)RootView.findViewById(R.id.texto);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=39.4561165,-0.3545661&sensor=false";
                direccion=url;
                Toast.makeText(getView().getContext(), direccion, Toast.LENGTH_SHORT).show();

                new AsyncHttpTask().execute(url);

            }
        });

        return RootView;

    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;

            Integer result = 0;

            try {

                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();

                if (statusCode ==  200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String response = convertInputStreamToString(inputStream);
                    parseResult(response);
                    result = 1;

                }else{
                    result = 0;
                }

            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }

            return result;
        }


        @Override
        protected void onPostExecute(Integer result) {

            if(result == 1){
                texto.setText(direccion);
                Toast.makeText(getView().getContext(), direccion, Toast.LENGTH_SHORT).show();

            }else{
                Log.e(TAG, "Failed to fetch data!");
            }
        }

    }
    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }
    private void parseResult(String result) {

        try{
            JSONObject response = new JSONObject(result);

            JSONArray resultado = response.optJSONArray("results");
            direccion = "No hay datos";


            JSONObject post = resultado.optJSONObject(0);
            direccion = post.getString("formatted_address");


        }catch (JSONException e){
            Log.e("Error","error",e);
        }
    }
}
