package com.example.gainyourmuscle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    //FIREBASE
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    //VIEWS FROM PROFILE FRAGMENT
    ImageView avatarTv;
    TextView nameTv,emailTv,genderTv,dobTv,phoneTv;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_profile, container, false);

       //init firebase
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        //init views
        avatarTv = view.findViewById(R.id.avatarTv);
        nameTv = view.findViewById(R.id.nameTv);
        emailTv = view.findViewById(R.id.emailTv);
        genderTv = view.findViewById(R.id.genderTv);
        dobTv = view.findViewById(R.id.dobTv);
        phoneTv = view.findViewById(R.id.phoneTv);

        //Getting information of current logged in user
        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //checks until required data get
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //get data
                    String name = ""+ ds.child("name").getValue();
                    String email = ""+ ds.child("email").getValue();
                    String gender = ""+ ds.child("gender").getValue();
                    String dob = ""+ ds.child("dateOfBirth").getValue();
                    String phone = ""+ ds.child("phone").getValue();
                    String image = ""+ ds.child("image").getValue();

                    //set DATA
                    nameTv.setText(name);
                    emailTv.setText(email);
                    genderTv.setText(gender);
                    dobTv.setText(dob);
                    phoneTv.setText(phone);
                    try{
                        //image successfully upload
                        Picasso.get().load(image).into(avatarTv);

                    }catch (Exception e){
                        //default uploaded
                        Picasso.get().load(R.drawable.ic_add_a_photo_black_24dp).into(avatarTv);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



       return view;
    }
}
