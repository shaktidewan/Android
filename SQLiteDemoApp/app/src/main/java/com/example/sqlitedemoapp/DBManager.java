package com.example.sqlitedemoapp;

import android.content.ContentValues;
import android.content.Context;
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

    public DBManager(Context context) {
        super(context, dbname, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN1 + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN2 + "TEXT,"
                + COLUMN3 + "TEXT,"
                + COLUMN4 + "TEXT"
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_student");
        onCreate(db);
    }
}

