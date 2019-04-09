package com.example.lockerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtV;
    EditText Password;
    EditText Name;
    Button changed;
    static String user, pass;
    private MyDBHandler dbHandler;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtV = (TextView) findViewById(R.id.txtvSignUp);
        Password = (EditText) findViewById(R.id.passw);
        Name = (EditText) findViewById(R.id.name);
        changed = (Button) findViewById(R.id.change);
        dbHandler = new MyDBHandler(getApplicationContext());
        db = dbHandler.getWritableDatabase();

        txtV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                Intent it = new Intent(getApplicationContext(), signup.class);
                startActivity(it);
                finish();
            }
        });
    }

    public void log(View V) {
        EditText userName = (EditText) findViewById(R.id.name);
        EditText passInput = (EditText) findViewById(R.id.passw);
        user = userName.getText().toString();
        pass = passInput.getText().toString();
        if ( user.isEmpty()||pass.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "PLease Fill missing data ", Toast.LENGTH_LONG).show();
            return;
        }

        String sqlemail = "SELECT * FROM "+ dbHandler.TABLE_NAME
                + " where " + dbHandler.COLUMN_EMAIL + " = ?";
        Cursor u = db.rawQuery(sqlemail, new String[] {user});


        dbHandler = new MyDBHandler(MainActivity.this);
        boolean storedPassword = dbHandler.getData(user, pass);

        if(!u.moveToFirst())
        {
            Toast.makeText(getApplicationContext(), "No Email has matched ", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!storedPassword){
            Toast.makeText(this,"please Confirm password",Toast.LENGTH_LONG).show();

        }else if (storedPassword){
            Intent intent = new Intent(this,home.class);
            intent.putExtra("email",user);
            startActivity(intent);
            finish();
        }


    }

}