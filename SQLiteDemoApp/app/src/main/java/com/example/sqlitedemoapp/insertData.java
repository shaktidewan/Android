package com.example.sqlitedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class insertData extends AppCompatActivity {
    EditText t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        t1=(EditText) findViewById(R.id.t1);
        t2=(EditText) findViewById(R.id.t2);
        t2=(EditText) findViewById(R.id.t3);
    }

}
