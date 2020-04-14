package com.example.recyclerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       rv = findViewById(R.id.rv_id);

        LinearLayoutManager lilama =  new LinearLayoutManager(this);
        lilama.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(lilama);

        List <SingerModelClass> singerList = new ArrayList<>();
        singerList.add(new SingerModelClass(R.drawable.ic_launcher_background,"Arjit","Melody"));
        singerList.add(new SingerModelClass(R.drawable.ic_launcher_foreground,"Queen","Rock"));
        singerList.add(new SingerModelClass(R.drawable.ic_launcher_background,"Underside","Metal"));
        singerList.add(new SingerModelClass(R.drawable.ic_launcher_background,"Cigarette after sex","pop"));
        singerList.add(new SingerModelClass(R.drawable.ic_launcher_foreground,"The Edge","Melody"));

        SingerAdapter haudeAdpater = new SingerAdapter(singerList);
        rv.setAdapter(haudeAdpater);
    }
}
