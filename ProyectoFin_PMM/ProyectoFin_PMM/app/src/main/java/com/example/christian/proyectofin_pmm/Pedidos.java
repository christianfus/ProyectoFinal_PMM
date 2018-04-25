package com.example.christian.proyectofin_pmm;

import java.io.Serializable;

/**
 * Created by Christian on 30/01/2018.
 */

public class Pedidos implements Serializable {
    Integer id;
    String fecha;
    String kebab;
    String cantidad;
    double precio;

    public Pedidos (Integer id, String fecha, String kebab, String cantidad, double precio){
        this.id = id;
        this.fecha = fecha;
        this.kebab = kebab;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getId() {return id;}
    public String getFecha() {return fecha;}
    public String getKebab() {return kebab;}
    public String getCantidad() {return cantidad;}
    public double getPrecio() {return precio;}
}



