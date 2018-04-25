package com.example.christian.proyectofin_pmm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Christian on 30/01/2018.
 */

public class OperacionesSQL {
    protected static void insert_pedidios(SQLiteDatabase sqLiteDatabase, Pedidos pedidos){
        sqLiteDatabase.execSQL("INSERT INTO pedidos (id_pedido, kebab_pedido, cantidad_pedido" +
                ",precio_pedido, fecha_pedido) VALUES " +
                "('"+ pedidos.getId()+"', '" +pedidos.getKebab()+"' ,'"+pedidos.getCantidad()+"', " +
                "'"+pedidos.getPrecio()+"', '"+pedidos.getFecha()+"');");
    }
    protected static Cursor select_pedidos(SQLiteDatabase sqLiteDatabase, String username, String fechaactual){
        return sqLiteDatabase.rawQuery("SELECT id, name_task, date_task, description, username FROM tareas where username = " +
                "'"+username+"' and state = 'false' and date_task = '"+fechaactual+"';", null);
    }
    protected static void update_tarea(SQLiteDatabase sqLiteDatabase, Integer id){
        sqLiteDatabase.execSQL("UPDATE tareas set state = 'true' where id = "+id+";");
    }
    protected static void delete_tarea(SQLiteDatabase sqLiteDatabase, Integer id){
        sqLiteDatabase.execSQL("DELETE from tareas where id = '"+id+"';");
    }
    protected static void delete_usuario(SQLiteDatabase sqLiteDatabase, String username){
        sqLiteDatabase.execSQL("DELETE from usuarios where username = '" +username+"';");
    }
}
