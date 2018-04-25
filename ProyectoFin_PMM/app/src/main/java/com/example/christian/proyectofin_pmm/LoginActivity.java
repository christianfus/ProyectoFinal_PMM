package com.example.christian.proyectofin_pmm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        final EditText _txtemail = (EditText) findViewById(R.id.txtemail);
        final EditText _txtpass = (EditText) findViewById(R.id.txtpass);
        Button _btnlogin = (Button) findViewById(R.id.btnsignin);
        TextView _btnreg = (TextView) findViewById(R.id.btnreg);

        dbhelper = new SQLiteDBHelper(this);
        db = dbhelper.getReadableDatabase();

        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = _txtemail.getText().toString();
                String pass = _txtpass.getText().toString();

                try{
                    cursor = db.rawQuery("SELECT * FROM " + SQLiteDBHelper.TABLE_NAME_2 +
                            " WHERE " + SQLiteDBHelper.COLUMN_EMAIL + "= ? AND " +
                            SQLiteDBHelper.COLUMN_PASSWORD + "= ?", new String[]{email, pass});
                }catch(Exception e){
                    Toast toast1 = Toast.makeText(getApplicationContext(),
                            "AAAA",
                            Toast.LENGTH_SHORT);

                    toast1.show();
                }

                    if (cursor != null) {
                        if (cursor.getCount() > 0) {

                            cursor.moveToFirst();
                            String _fname = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_FULLNAME));
                            String _email = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_EMAIL));
                            int _id = cursor.getInt(cursor.getColumnIndex(SQLiteDBHelper.COLUMN_ID_2));

                            Toast.makeText(LoginActivity.this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("fullname", _fname);
                            intent.putExtra("email", _email);
                            intent.putExtra("id", _id);
                            startActivity(intent);

                            finish();
                        } else {
                            Toast toast1 = Toast.makeText(getApplicationContext(),
                                    "Usuario o contraseña incorrectos",
                                    Toast.LENGTH_SHORT);

                            toast1.show();
                        }
                    }


            }
        });

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegisterAccountActivity.class);
                startActivity(intent);
            }
        });

    }

}
