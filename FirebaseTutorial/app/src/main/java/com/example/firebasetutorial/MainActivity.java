package com.example.firebasetutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText eText;
    private TextView vText;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eText = findViewById(R.id.editTexit1);
        vText = findViewById(R.id.textView2);

        //Database instantiation
        mDatabase =FirebaseDatabase.getInstance();
        //mRef= mDatabase.getReference();//root ma janxa databaseko
        //tala ko child nagari yaha ne yesto garna milxa vaiharu
        mRef= mDatabase.getReference("users");
    }

    public void Insert(View v) {

        String data =eText.getText().toString();
        //mRef.child("user").setValue(data); //for child database
        mRef.child("user1").setValue(data);
        Toast.makeText(this, "Data insert", Toast.LENGTH_SHORT).show();
    }

    public void Read(View v) {
        //.child halnu parxa yedi child lai read garne ho vane kina ki hamile child insert matra haleko chum ne ta
        mRef.child("user1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // "<>" means generic class in java
                String data = dataSnapshot.getValue(String.class);
                vText.setText(data);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
//addValueEventListener yo method le aafai update hunxa tara aadlistnerSingleValueEvent le chai update gardiana
