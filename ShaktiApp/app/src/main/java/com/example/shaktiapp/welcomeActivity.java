package com.example.shaktiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

public class welcomeActivity extends AppCompatActivity {
    String [] arrays = {"SHAKTI","Bijay","RAJU","Pritika","Ramesh"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ArrayAdapter adapt = new ArrayAdapter(this,R.layout.item_layout,arrays);
        GridView lv = findViewById(R.id.lv_id);

        lv.setAdapter(adapt);

    }
}
