package com.example.haudedatabase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //change nahune variables
    public static final int DB_VERSION =1;
    public static final String DB_NAME= "haude Database";
    public static final String TABLE_NAME="Products";
    public static final String COL_ONE="product_id";
    public static final String COL_TWO="product_name";

    //construcotr le db banaune kaam garxa
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    //Table creation ko kaam yaha hunxa sala haru
        String query =
                "CREATE TABLE " + TABLE_NAME + "(" + COL_ONE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TWO + " TEXT" + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //naya version db banaune

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);//naya db banako hai guys
    }

    public void insertProduct(Products products){
        ContentValues values = new ContentValues();
        values.put(COL_TWO, products.getProduct_name());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //delete product from database
    public void removeProduct(String entered_product){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COL_TWO + "= ?",new String[] {entered_product});
        db.close();
    }
    //display data of database
    public String db_Display(){
        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query ="SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(query,null);

        //cursor ma loop gardai,data dbString ma rakhne ani dbString lai return gardine

        if(c.moveToFirst()){
            do{
                dbString = dbString + c.getString(c.getColumnIndex(COL_TWO));
                dbString = dbString +"\n";
            }while (c.moveToNext());



        }
        c.close();
        db.close();
        return  dbString;
    }
}
