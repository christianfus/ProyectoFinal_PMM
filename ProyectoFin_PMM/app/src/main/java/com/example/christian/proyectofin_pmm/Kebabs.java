package com.example.christian.proyectofin_pmm;

import java.io.Serializable;

public class Kebabs implements Serializable{
    private  String nombre;
    private  String descripcion;
    private  double precio;
    private  int imagen;


    public Kebabs (String nombre, String descripcion, double precio, int imagen){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public  String getnombre (){
        return nombre;
    }
    public  String getdescripcion (){return descripcion;}
    public  double getPrecio (){
        return precio;
    }
    public  int getImagen () {return imagen;}
}
