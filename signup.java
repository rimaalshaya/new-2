package com.example.lockerapp;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class signup extends AppCompatActivity {
    private EditText etName, etPhone , etPass , etEmail , etCredit ;
    private MyDBHandler dbHandler;
    Button go;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText) findViewById(R.id.edName);
        etPhone = (EditText) findViewById(R.id.edPhone);
        etPass = (EditText) findViewById(R.id.edPass);
        etEmail = (EditText) findViewById(R.id.edEmil);
        etCredit = (EditText) findViewById(R.id.edCredit);
        dbHandler = new MyDBHandler(getApplicationContext());
        db = dbHandler.getWritableDatabase();

    }

    public void signUp(View V){
        String nameStr = etName.getText().toString();
        String phoneStr = etPhone.getText().toString();
        String passStr = etPass.getText().toString();
        String emailStr = etEmail.getText().toString();
        String creditStr = etCredit.getText().toString();

        final int min = 20;
        final int max = 80;
        final int random = new Random().nextInt((max - min) + 1) + min;
        final String locker = String.valueOf(random);
        db = dbHandler.getWritableDatabase();

        ArrayList<String> email = new ArrayList<>();
        Cursor cursor=db.rawQuery("SELECT Email FROM "+dbHandler.TABLE_NAME,null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            email.add(cursor.getString(cursor.getColumnIndex(dbHandler.COLUMN_EMAIL)));
            cursor.moveToNext();
        }

        if ( nameStr.isEmpty() || phoneStr.isEmpty() || passStr.isEmpty() || emailStr.isEmpty()|| creditStr.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "PLease Fill missing data ", Toast.LENGTH_LONG).show();
            return;
        }else if (email.contains(emailStr)){
            Toast.makeText(getApplicationContext(),"this email is already registered!",Toast.LENGTH_LONG).show();
            return;
        }else{
            etName.setText("");
            etPhone.setText("");
            etCredit.setText("");
            etEmail.setText("");
            etPass.setText("");
            dbHandler.addContact(new Accounts(nameStr,passStr,emailStr,phoneStr,creditStr,locker));

            String tstMsg ="Your account is inserted \n" + "Your Locker is "+random ;
            Toast.makeText(getApplicationContext(), tstMsg, Toast.LENGTH_LONG).show();
        }

        db.close();



    }

    public void cancle(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }


}