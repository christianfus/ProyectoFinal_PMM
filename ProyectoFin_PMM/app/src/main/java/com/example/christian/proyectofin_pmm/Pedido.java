package com.example.christian.proyectofin_pmm;

import java.io.Serializable;

/**
 * Created by Christian on 17/02/2018.
 */

public class Pedido implements Serializable{
    int id;
    String name;
    String descripcion;
    int precio;

    public Pedido() {}

    public Pedido(int id, String name, String descripcion, int precio) {
        setId(id);
        setName(name);
        setDescripcion(descripcion);
        setPrecio(precio);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
