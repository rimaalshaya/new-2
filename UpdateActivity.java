package com.example.lockerapp;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    String name,email,pass,phone,credit,locker;
    private EditText etName, etPhone , etPass , etEmail , etCredit ;
    MyDBHandler dbHandler;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etName = (EditText) findViewById(R.id.edName);
        etPhone = (EditText) findViewById(R.id.edPhone);
        etPass = (EditText) findViewById(R.id.edPass);
        etEmail = (EditText) findViewById(R.id.edEmil);
        etCredit = (EditText) findViewById(R.id.edCredit);
        dbHandler = new MyDBHandler(getApplicationContext());
        db = dbHandler.getWritableDatabase();

        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        pass=getIntent().getStringExtra("password");
        phone=getIntent().getStringExtra("phone");
        credit=getIntent().getStringExtra("credit");
        locker=getIntent().getStringExtra("locker");
        etName.setText(name);
        etEmail.setText(email);
        etCredit.setText(credit);
        etPass.setText(pass);
        etPhone.setText(phone);

    }

    public void update(View view)
    {
        ContentValues values = new ContentValues();
        values.put(MyDBHandler.COLUMN_NAME, etName.getText().toString()); // user Name
        values.put(MyDBHandler.COLUMN_PASS, etPass.getText().toString()); // user Phone
        values.put(MyDBHandler.COLUMN_EMAIL, etEmail.getText().toString()); // user email
        values.put(MyDBHandler.COLUMN_CREDIT, etCredit.getText().toString()); // user credit
        values.put(MyDBHandler.COLUMN_PHONE, etPhone.getText().toString()); //user phone
        values.put(MyDBHandler.COLUMN_LOCKER,locker);//user locker
        db.update(MyDBHandler.TABLE_NAME, values, dbHandler.COLUMN_LOCKER + "=" + locker, null);
        Toast.makeText(getApplicationContext(),"Your account data has been updated",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,MainActivity.class));
    }

    public void c(View view) {
        startActivity(new Intent(getApplicationContext(),home.class));
        finish();
    }
}