package com.example.shaktiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText loginUser,loginPass;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginPass = (EditText) findViewById(R.id.uname);
        loginUser = (EditText) findViewById(R.id.upass);
        btn =(Button) findViewById(R.id.login_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //auth
                String name = loginUser.getText().toString();
                String password = loginPass.getText().toString();

                if (name.isEmpty() ){
                    Toast.makeText(MainActivity.this, "User Blank", Toast.LENGTH_SHORT).show();

                } else if (password.isEmpty() ){
                    Toast.makeText(MainActivity.this, "Password Blank", Toast.LENGTH_SHORT).show();

                }
                else if(name.equals("admin") && password.equals("admin")){
                    startActivity(new Intent(MainActivity.this,welcomeActivity.class));
                }else{
                    Toast.makeText(MainActivity.this, "Invalid Vo hau", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
