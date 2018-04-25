package com.example.christian.proyectofin_pmm;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterAccountActivity extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        openHelper = new SQLiteDBHelper(this);

        final EditText _txtfullname = (EditText) findViewById(R.id.txtname_reg);
        final EditText _txtemail = (EditText) findViewById(R.id.txtemail_reg);
        final EditText _txtpass = (EditText) findViewById(R.id.txtpass_reg);
        final EditText _txtmobile = (EditText) findViewById(R.id.txtmobile_reg);
        Button _btnreg = (Button) findViewById(R.id.btn_reg);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = openHelper.getWritableDatabase();

                String fullname = _txtfullname.getText().toString();
                String email = _txtemail.getText().toString();
                String pass = _txtpass.getText().toString();
                String mobile = _txtmobile.getText().toString();


                InsertData(fullname, email, pass, mobile);

                final AlertDialog.Builder builder = new AlertDialog.Builder(RegisterAccountActivity.this);
                builder.setTitle("Información");
                builder.setMessage("Tu cuenta ha sido creada correctamente.");
                builder.setPositiveButton("Genial", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        dialogInterface.dismiss();
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    public void InsertData(String fullName, String email, String password, String mobile ) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper.COLUMN_FULLNAME,fullName);
        values.put(SQLiteDBHelper.COLUMN_EMAIL,email);
        values.put(SQLiteDBHelper.COLUMN_PASSWORD,password);
        values.put(SQLiteDBHelper.COLUMN_MOBILE,mobile);
        long id = db.insert(SQLiteDBHelper.TABLE_NAME_2,null,values);

    }

}
