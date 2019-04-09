package com.example.lockerapp;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
    TextView tName, tPhone,tPass,tEmail,tCredit,tLocker;
    MyDBHandler dbHandler;
    private SQLiteDatabase db;
    String user;
    Button delete;
    String name,email,pass,phone,credit,locker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = getIntent().getStringExtra("email");
        // To read
        tName = (TextView) findViewById(R.id.name);
        tPhone = (TextView) findViewById(R.id.phone);
        tPass = (TextView) findViewById(R.id.password);
        tEmail = (TextView) findViewById(R.id.email);
        tCredit = (TextView) findViewById(R.id.Credit);
        tLocker = (TextView) findViewById(R.id.lock);
        delete=(Button) findViewById(R.id.delete) ;

        dbHandler = new MyDBHandler(this);
        db = dbHandler.getWritableDatabase();

        String sqlStmt = "SELECT * FROM "+ dbHandler.TABLE_NAME
                + " where " + dbHandler.COLUMN_EMAIL + " = ?";

        Cursor c = db.rawQuery(sqlStmt, new String[] {user});
        if(c.moveToFirst() && c.getCount() >= 1){
            do{
                name=c.getString(0);
                pass=c.getString(1);
                email=c.getString(2);
                phone=c.getString(3);
                credit=c.getString(4);
                locker=c.getString(5);
                tName.setText(name);
                tPass.setText(pass);
                tEmail.setText(email);
                tPhone.setText(phone);
                tCredit.setText(credit);
                tLocker.setText(locker);
            }while(c.moveToNext());}


        c.close();


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                db=dbHandler.getWritableDatabase();
                boolean del =deleteTitle(tEmail.getText().toString());
                if (del){
                    Toast.makeText(getApplicationContext(),"Your data has been deleted",Toast.LENGTH_LONG).show();
                    dbHandler.close();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),"the data base is empty",Toast.LENGTH_LONG).show();
                    dbHandler.close();
                    finish();
                }

            }
        });

    }


    public void update(View v)
    {
        Intent intent = new Intent(this,UpdateActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("password",pass);
        intent.putExtra("email",email);
        intent.putExtra("phone",phone);
        intent.putExtra("credit",credit);
        intent.putExtra("locker",locker);
        startActivity(intent);
        finish();
    }
    public void Exit(View v) {
        Intent i = new Intent (getApplicationContext(), MainActivity.class);
        startActivity(i);
        dbHandler.close();
        finish();
    }


    public boolean deleteTitle(String email) {

        try
        {
            int result=db.delete(MyDBHandler.TABLE_NAME,MyDBHandler.COLUMN_EMAIL+" =?",new String[]{String.valueOf(email)});
            if(result>0)
            {
                tName.setText("");
                tPhone.setText("");
                tCredit.setText("");
                tEmail.setText("");
                tPass.setText("");
                tLocker.setText("");
                return true;
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;

//            int count = dbHandler.getCount();
//            if (count > 0) {
//                tName.setText("");
//                tPhone.setText("");
//                tCredit.setText("");
//                tEmail.setText("");
//                tPass.setText("");
//                tLocker.setText("");
//                return db.delete(dbHandler.TABLE_NAME, dbHandler.COLUMN_EMAIL + "=" + email, null) > 0;
//            }else{
//                return false;
//            }


    }

}