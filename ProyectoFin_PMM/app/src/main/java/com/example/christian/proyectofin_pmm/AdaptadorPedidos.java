package com.example.christian.proyectofin_pmm;

/**
 * Created by Christian on 17/02/2018.
 */

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPedidos extends ArrayAdapter {

    Activity context;
    ArrayList <Pedido> lista;

    AdaptadorPedidos(Activity context, ArrayList <Pedido> lista) {

        super(context, R.layout.listitem_pedidos, lista);
        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = getView(position,convertView,parent);
        return view;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_pedidos, null);


        TextView com_id = (TextView)item.findViewById(R.id.com_id);
        com_id.setText(Integer.toString(lista.get(position).getId()));

        TextView com_name = (TextView)item.findViewById(R.id.com_name);
        com_name.setText(lista.get(position).getName());

        TextView com_descripcion = (TextView) item.findViewById(R.id.com_desc);
        com_descripcion.setText(lista.get(position).getDescripcion());

        TextView com_prec = (TextView) item.findViewById(R.id.com_precio);
        com_prec.setText(String.valueOf(lista.get(position).getPrecio()));

        return(item);
    }
}
