package com.example.christian.proyectofin_pmm;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Factura extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);

        Bundle objeto = getIntent().getExtras();
        Kebabs kebabs = (Kebabs) objeto.getSerializable("Kebabs");
        TextView modelokebabs = (TextView) findViewById(R.id.amodelo);
        TextView preciobase = (TextView) findViewById(R.id.apreciobase);
        TextView envio = (TextView) findViewById(R.id.aenvio);
        TextView extra = (TextView) findViewById(R.id.aextras);
        TextView unidades = (TextView) findViewById(R.id.aunidades);
        TextView costetotal = (TextView) findViewById(R.id.acostetotal);
        Button vuelta = (Button) findViewById(R.id.vuelta);
        final CheckBox hora = (CheckBox) findViewById(R.id.hora);
        final TextClock reloj = (TextClock) findViewById(R.id.reloj);

        modelokebabs.setText(kebabs.getnombre());
        preciobase.setText(String.valueOf(kebabs.getPrecio()) + "€");

        String queso = objeto.getString("Queso");
        String ingre = objeto.getString("Ingredientes");
        String tama = objeto.getString("Tamaño");
        if(queso==null){queso="";}
        if(ingre==null){ingre="";}
        if(tama==null){tama="";}
        extra.setText(queso+ingre+tama);

        unidades.setText(objeto.getString("numerokebabs"));
        envio.setText(objeto.getString("nombreentrega"));
        costetotal.setText(objeto.getString("Total"));

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hora.isChecked()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        reloj.getTimeZone();
                    }
                    reloj.setVisibility(View.VISIBLE);
                }else{
                    reloj.setVisibility(View.INVISIBLE);
                }
            }
        });
        //Callback
        vuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volver = new Intent(Factura.this, MainActivity.class);
                Bundle vuelta = new Bundle();
                vuelta.putString("hora", reloj.getText().toString());
                volver.putExtras(vuelta);
                setResult(RESULT_OK, volver);
                finish();
            }
        });
    }
}

