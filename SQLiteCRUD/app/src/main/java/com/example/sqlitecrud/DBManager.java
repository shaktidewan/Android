package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBManager extends SQLiteOpenHelper {
    private static final String dbname = "StudentDesc.db";
    private static final String TABLE_NAME = "tbl_student";
    private static final String COLUMN1 = "ID";
    private static final String COLUMN2 = "NAME";
    private static final String COLUMN3 = "EMAIL";
    private static final String COLUMN4 = "COURSE";

    public DBManager(@Nullable Context context) {
        super(context, dbname, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,COURSE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String email,String course){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv =new ContentValues();

        cv.put(COLUMN2,name);
        cv.put(COLUMN3,email);
        cv.put(COLUMN4,course);

        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }

    public Boolean updateData(String id,String name,String email,String course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN1,id);
        cv.put(COLUMN2,name);
        cv.put(COLUMN3,email);
        cv.put(COLUMN4,course);
        db.update(TABLE_NAME,cv,"ID = ?",new String[]{id});
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{ id });
    }
}
