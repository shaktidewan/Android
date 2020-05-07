package com.example.block7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NumberPicker possiblities;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        possiblities = (NumberPicker) findViewById(R.id.num_pick);
        String[] possibilitesStrings ={
                "Andorid",
                "CheckList text-input field",
                "Coursera",
                "Supelec"
        };
        possiblities.setDisplayedValues(possibilitesStrings);
        possiblities.setMinValue(0);
        possiblities.setMaxValue(possibilitesStrings.length-1);
    }

    public void navigate(View view) {
        int choice = possiblities.getValue();
        if(choice == 0)
        webView.loadUrl("file:///android_asset/home.htlm");
        else if(choice == 1)
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        else if(choice == 2)
            Toast.makeText(this, "Hheheh", Toast.LENGTH_SHORT).show();
        else if(choice == 3)
            Toast.makeText(this, "hahaha", Toast.LENGTH_SHORT).show();

    }
}
