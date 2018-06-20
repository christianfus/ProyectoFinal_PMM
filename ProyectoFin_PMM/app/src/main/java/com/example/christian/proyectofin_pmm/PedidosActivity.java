package com.example.christian.proyectofin_pmm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;

public class PedidosActivity extends FragmentActivity {
    Spinner spinner;
    Intent intent;
    Pedido item = null;
    ArrayList<Pedido> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lista = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);


        final int id = 1;

        spinner = (Spinner) findViewById(R.id.spinner);
        cargaSpinner(id);

    }

    private void cargaSpinner(int id) {


        SQLiteDatabase db = new SQLiteDBHelper(this).getWritableDatabase();
        for(int i = 0; i < 10; i++) {
            String query = "select * from pedidos where pedidoid = " + i;
            Cursor c = db.rawQuery(query, null);

            while (c.moveToNext()) {
                int pedido_id = c.getInt(c.getColumnIndex(SQLiteDBHelper.COLUMN_ID));
                String nom = c.getString(c.getColumnIndex(SQLiteDBHelper.COLUMN_NAME));
                String desc = c.getString(c.getColumnIndex(SQLiteDBHelper.COLUMN_DESC));
                int precio = c.getInt(c.getColumnIndex(SQLiteDBHelper.COLUMN_PRECIO));

                item = new Pedido();
                item.id = pedido_id;
                item.name = nom;
                item.descripcion = desc;
                item.precio = precio ;
                lista.add(item);

            }
        }


        AdaptadorPedidos adaptador = new AdaptadorPedidos(this, lista);

        spinner.setAdapter(adaptador);
    }
}

