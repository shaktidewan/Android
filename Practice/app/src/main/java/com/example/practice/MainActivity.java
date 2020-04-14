package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button dialPhone,toast,showLocation,openWebPage,viewIntent,letsGetPro;
    public static final String EXTRA_TEXT="com.example.practice.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER="com.example.practice.example.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =(TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        dialPhone =(Button) findViewById(R.id.dialNumber);
        toast =(Button) findViewById(R.id.toast);
        showLocation =(Button) findViewById(R.id.showLocation);
        openWebPage =(Button) findViewById(R.id.openWebPage);
        viewIntent =(Button) findViewById(R.id.viewIntent);
        letsGetPro =(Button) findViewById(R.id.letsGetPro);

        dialPhone.setOnClickListener(this);
        toast.setOnClickListener(this);
        showLocation.setOnClickListener(this);
        openWebPage.setOnClickListener(this);
        viewIntent.setOnClickListener(this);
        letsGetPro.setOnClickListener(this);
    }

    public void openActivity2() {
        EditText editText1 = (EditText) findViewById(R.id.edittext1);
        String text = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.edittext2);
        int number = Integer.parseInt(editText2.getText().toString());

        Intent intent = new Intent(this,otherActivity.class);
        intent.putExtra(EXTRA_TEXT,text);
        intent.putExtra(EXTRA_NUMBER,number);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
    switch (view.getId())
    {
        case R.id.dialNumber :
            //Dial happen to be an android component...
            //Can we create our own component...???????

            Intent intent1= new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9810138740"));
            startActivity(intent1);

            break;
        case R.id.toast :

            Intent intent = new Intent(MainActivity.this,otherActivity.class);
            startActivity(intent);
            break;
        case R.id.showLocation :

            Intent intent2= new Intent(Intent.ACTION_VIEW,Uri.parse("geo:27.7393422,85.3344328,17?z=9"));
            startActivity(intent2);
            break;

        case R.id.openWebPage :

            Intent intent3= new Intent(Intent.ACTION_VIEW,Uri.parse("https://introcs.cs.princeton.edu/"));
            startActivity(intent3);
            break;

        case R.id.viewIntent :


            break;
        case R.id.letsGetPro :


            break;
    }
    }
}
