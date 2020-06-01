package com.example.firebaseapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText mEmailEt,mPasswordEt;
    Button mRegisterBtn;
    private FirebaseAuth mAuth;
    TextView mHaveAccountTv;

    //Progressbar to display while registeration of user
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Actionbar and its title

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Account");
        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //Init
        mEmailEt =findViewById(R.id.emailET);
        mPasswordEt=findViewById(R.id.passwordET);
        mRegisterBtn=findViewById(R.id.registerBtn);
        mHaveAccountTv=findViewById(R.id.have_accountTv);
       //init firabase
        mAuth = FirebaseAuth.getInstance();

        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Registration...");

        //handle register btn click
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input email,passowrd
                String email=mEmailEt.getText().toString().trim();
                String password=mPasswordEt.getText().toString().trim();
                //Validate
                if ( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    mEmailEt.setError("Invalid Email");
                    mEmailEt.setFocusable(true);

                }
                else if (password.length()<6){
                    mPasswordEt.setError("Password is too short");
                    mPasswordEt.setFocusable(true);
                }
                else {
                    registerUser(email,password);
                }

            }
        });

        //handle login textview
        mHaveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser(final String email, String password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            progressDialog.dismiss();

                            FirebaseUser user = mAuth.getCurrentUser();

                            //GET USER EMAIL AND UID FROM AUTH
                            String email = user.getEmail();
                            String uid=user.getUid();
                            //when user is registered store user info in firebase realtime database too

                            //USING HASHMAP FOR USERS
                            HashMap<Object, String> hashMap = new HashMap<>();

                            //put information in hashmap
                            hashMap.put("email", email);
                            hashMap.put("uid",uid);
                            hashMap.put("name","");//we will add late(e.g. edit profile)
                            hashMap.put("phone","");//we will add late(e.g. edit profile)
                            hashMap.put("image","");//we will add late(e.g. edit profile)
                            //firebase databse instance
                            FirebaseDatabase database =FirebaseDatabase.getInstance();
                            //path to store use data named "Users"
                            DatabaseReference reference = database.getReference("Users");
                            reference.child(uid).setValue(hashMap);


                            Toast.makeText(RegisterActivity.this, "User Registered..."+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();//go previous activity
        return super.onSupportNavigateUp();
    }
}
