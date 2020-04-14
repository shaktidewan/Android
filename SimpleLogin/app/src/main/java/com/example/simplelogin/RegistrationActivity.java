package com.example.simplelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private EditText userName,userEmail,userPassword;
    private Button SignInBtn;
    private TextView TValreadySigned;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //Upload data to database
                    String user_email= userEmail.getText().toString().trim();
                    String user_password= userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }else{
                                Toast.makeText(RegistrationActivity.this,"Unsuccessful",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }

    private  void setupUIViews(){
        userName = (EditText) findViewById(R.id.editName);
        userPassword = (EditText) findViewById(R.id.editPassword);
        userEmail = (EditText) findViewById(R.id.editEmail);
        SignInBtn = (Button) findViewById(R.id.btnRegister);
        TValreadySigned = (TextView) findViewById(R.id.alreadySigned);


    }

    private Boolean validate(){
    Boolean result =false;

    String name=userName.getText().toString();
    String password=userPassword.getText().toString();
    String email=userEmail.getText().toString();

    if(name.isEmpty() && password.isEmpty() && email.isEmpty()){
        Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();

    }else{
        result=true;
    }
    return result;
    }
}
