package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID="ID";
    private static final String KEY_NAME="name";
    private static final String KEY_PH_NO ="phone_number";


    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE "+ TABLE_CONTACTS +"("
                +KEY_ID +"I NTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                +KEY_PH_NO + " TEXT"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);

        //CREATE TABLES AGAIN
        onCreate(db);
    }

    void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(KEY_NAME,contact.getName()); //COntact Name
        values.put(KEY_PH_NO,contact.getPhoneNumber());

        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();

        String selectQuery ="SELECT * FROM "+ TABLE_CONTACTS;

        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToNext()){
            do{
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                //adding to list
                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }
}
