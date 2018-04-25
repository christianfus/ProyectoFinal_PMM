package com.example.christian.proyectofin_pmm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Christian on 30/01/2018.
 */

public class BDPedidosSQLiteHelper extends SQLiteOpenHelper {
    //CREAR TABLAS NADA MAS CREAR LA BASE DE DATOS, SI NO, NO INSERTARA MAS TABLAS
    public BDPedidosSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
        super (context, nombre, almacen, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE pedidos (" +
                "id_pedido integer PRIMARY KEY AUTOINCREMENT," +
                "kebab_pedido text not null," +
                "cantidad_pedido text not null," +
                "fecha_pedido date not null," +
                "precio_pedido BigInt not null)");
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pedidos");
        onCreate(sqLiteDatabase);
    }
}
