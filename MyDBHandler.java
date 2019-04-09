package com.example.lockerapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {


    // Variables for database info
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "users.db";

    public static final String TABLE_NAME = "userdata";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASS = "pass";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_CREDIT = "credit";
    public static final String COLUMN_LOCKER= "locker";


    public MyDBHandler (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //Log.d("DB","constructor");
    }


    @Override
    public void onCreate(SQLiteDatabase db){

        // To create all tables
        String sqlStmt = "CREATE TABLE " + TABLE_NAME+ "(" +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_PASS + " TEXT NOT NULL," +
                COLUMN_EMAIL + " TEXT NOT NULL," +
                COLUMN_PHONE + " TEXT NOT NULL," +
                COLUMN_CREDIT + " TEXT NOT NULL," +
                COLUMN_LOCKER + " TEXT NOT NULL)";


        Log.d("DB", "Created");
        db.execSQL(sqlStmt);

    }

    void addContact(Accounts accounts) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, accounts.getUserName()); // Contact Name
        values.put(COLUMN_PASS, accounts.getPassWord()); // Contact Phone
        values.put(COLUMN_EMAIL, accounts.getEmail()); // Contact email
        values.put(COLUMN_CREDIT, accounts.getCredit()); // Contact credit
        values.put(COLUMN_PHONE, accounts.getPhoneNumber()); //contact phone
        values.put(COLUMN_LOCKER,accounts.getLocker());//contact locker


        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Log.d("DB","Tha table has been dropped");
        onCreate(db);
    }


    public boolean getData(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE Email=? AND pass=?", new String[]{email,password});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                mCursor.close();
                return true;
            }
        }
        return false;
    }

    public int getCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

}