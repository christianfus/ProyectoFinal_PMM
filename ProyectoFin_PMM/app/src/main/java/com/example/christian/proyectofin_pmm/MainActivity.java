package com.example.christian.proyectofin_pmm;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    private Kebabs[] datos = new Kebabs[]{
            new Kebabs("KEBAB ", "DE POLLO", 4, R.drawable.kebab1),
            new Kebabs("KEBAB ", "DE TERNERA", 3, R.drawable.kebab2),
            new Kebabs("FALAFEL ", "DE GARBANZOS", 5, R.drawable.kebab3)
    };

    int[] imagenes = {R.drawable.kebab1, R.drawable.kebab2, R.drawable.kebab3};

    private Object[] calculos;
    private double total;
    private Spinner kebabs;
    AdaptadorKebabs adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = new SQLiteDBHelper(this);

        //Para rellenar el spinner
        adaptador = new AdaptadorKebabs(this);
        kebabs = (Spinner) findViewById(R.id.kebab);
        kebabs.setAdapter(adaptador);

        Button calcular = (Button)findViewById(R.id.calcular);
        final RadioGroup entrega = (RadioGroup)findViewById(R.id.entrega);
        final Button local = (Button)findViewById(R.id.local);
        final Button domicilio = (Button)findViewById(R.id.domicilio);
        final EditText numerokebabs = (EditText)findViewById(R.id.numerokebabs);
        final CheckBox tamano = (CheckBox)findViewById(R.id.tamano);
        final CheckBox ingredientes = (CheckBox)findViewById(R.id.ingredientes);
        final CheckBox queso = (CheckBox)findViewById(R.id.queso);

        //Al clickar el spinner


        //Cálculos precio Kebab
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paso = new Intent(MainActivity.this, Factura.class);

                Bundle pasoobjetos = new Bundle();

                double precioKebab = datos[kebabs.getSelectedItemPosition()].getPrecio();
                Kebabs seleccion = new Kebabs (datos[kebabs.getSelectedItemPosition()].getnombre(),
                        datos[kebabs.getSelectedItemPosition()].getdescripcion(),
                        datos[kebabs.getSelectedItemPosition()].getPrecio(),
                        datos[kebabs.getSelectedItemPosition()].getImagen());
                pasoobjetos.putSerializable("Kebabs", seleccion);
                paso.putExtras(pasoobjetos);

                total =  Double.parseDouble(numerokebabs.getText().toString()) * datos[kebabs.getSelectedItemPosition()].getPrecio();

                if (numerokebabs.getText().toString().isEmpty()){
                    numerokebabs.setText("0");
                }

                paso.putExtra("numerokebabs", numerokebabs.getText().toString());
                paso.putExtra("preciokebab", String.valueOf(precioKebab));

                double tarifa = 0;
                String ntarifa = " ";
                if (entrega.getCheckedRadioButtonId() == R.id.local){
                    ntarifa = local.getText().toString();
                    tarifa = 0;
                    ntarifa = local.getText().toString();
                }
                if (entrega.getCheckedRadioButtonId() == R.id.domicilio){
                    ntarifa = domicilio.getText().toString();
                    tarifa = (total * 10 ) / 100;
                    ntarifa = domicilio.getText().toString();
                }
                paso.putExtra("nombreentrega", ntarifa);
                paso.putExtra("tarifa", String.valueOf(tarifa));
                total = total + tarifa;


                boolean checked = false;
                if(tamano.isChecked()){
                    checked = true;
                    paso.putExtra("Tamaño ", tamano.getText().toString());
                    paso.putExtra("checked", checked);
                    total += 2;
                }
                if(queso.isChecked()){
                    checked = true;
                    paso.putExtra("Queso", queso.getText().toString());
                    paso.putExtra("checked2", checked);
                    total+=2;
                }
                if(ingredientes.isChecked()){
                    checked = true;
                    paso.putExtra("Ingredientes ", ingredientes.getText().toString());
                    paso.putExtra("checked2", checked);
                    total+=2;
                }
                paso.putExtra("Total",String.valueOf(total));
                startActivity(paso);

                db = openHelper.getWritableDatabase();
                double kbb_precio = total;
                String kbb_name = datos[kebabs.getSelectedItemPosition()].getnombre();
                String kbb_desc = datos[kebabs.getSelectedItemPosition()].getdescripcion();
                InsertData(kbb_precio, kbb_name, kbb_desc);

                String mensaje = "Total = " + total;
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InsertData(double kbb_precio, String kbb_name, String kbb_desc) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_NAME,kbb_name);
        values.put(SQLiteDBHelper.COLUMN_DESC,kbb_desc);
        values.put(SQLiteDBHelper.COLUMN_PRECIO,kbb_precio);
        ;
        long id = db.insert(SQLiteDBHelper.TABLE_NAME,null,values);

    }

    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.acerca:
                Intent acerca = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(acerca);
                return true;
            case R.id.pedidos:
                Intent pedido = new Intent(MainActivity.this, PedidosActivity.class);
                startActivity(pedido);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class AdaptadorKebabs extends ArrayAdapter<Kebabs> {
        Activity context;

        AdaptadorKebabs(Activity context){
            super(context, R.layout.listitem_kebabs, datos);
            this.context = context;
        }
        public View getDropDownView (int position, View convertView, ViewGroup parent){
            View vistaDesplegada = getView(position, convertView, parent);
            return  vistaDesplegada;

        }

        public View getView(int i, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_kebabs, null);

            TextView Nombre = (TextView) item.findViewById(R.id.LblZona);
                Nombre.setText(datos[i].getnombre());

            TextView Descripcion = (TextView) item.findViewById(R.id.LblContinente);
                Descripcion.setText(datos[i].getdescripcion());

            TextView Precio = (TextView)item.findViewById(R.id.LblPrecio);
                Precio.setText(String.valueOf(datos[i].getPrecio()) + "€");

            ImageView Imagen = (ImageView)item.findViewById(R.id.img);
                Imagen.setImageResource(datos[i].getImagen());

            return (item);
        }

    }

}
