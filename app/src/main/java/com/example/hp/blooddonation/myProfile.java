package com.example.hp.blooddonation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myProfile extends AppCompatActivity {
     TextView profile_name, profile_email,profile_city,profile_gender,profile_bg,profile_number;
     String current_user_email;
     FirebaseAuth auth;
     DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        initVars();
        setDbValues();
    }

    private void setDbValues() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        current_user_email=user.getEmail();

        ref.addValueEventListener(new ValueEventListener() {
            int flag=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot child_email : dataSnapshot.getChildren()) {

                    if(current_user_email.equals(child_email.child("user_email").getValue().toString())){
                        profile_name.setText(child_email.child("user_name").getValue().toString());
                        profile_email.setText(child_email.child("user_email").getValue().toString());
                        profile_city.setText(child_email.child("location").getValue().toString());

                        profile_number.setText(child_email.child("user_contact").getValue().toString());
                        profile_bg.setText(child_email.child("bloodgroup").getValue().toString());
                        profile_gender.setText(child_email.child("user_gender").getValue().toString());
                        flag=1;

                    }

                }
                if(flag==0){
                    Toast.makeText(myProfile.this, "Email is Wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @SuppressLint("WrongViewCast")
    private void initVars() {
        profile_name=findViewById(R.id.txt_profile_name);
        profile_city=findViewById(R.id.txt_profile_city);
        profile_gender=findViewById(R.id.txt_profile_gender);
        profile_bg=findViewById(R.id.txt_profile_bgroup);
        profile_email=findViewById(R.id.txt_profile_email);
        profile_number=findViewById(R.id.txt_profile_number);

        auth= FirebaseAuth.getInstance();
        ref=FirebaseDatabase.getInstance().getReference("Users");
    }
}
