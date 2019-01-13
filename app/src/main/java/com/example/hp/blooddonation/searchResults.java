package com.example.hp.blooddonation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class searchResults extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    users_adapter adapter;
    ArrayList<Users> users_list;
    DatabaseReference myref;
    String location,bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);
        Intent i=getIntent();
        location=i.getStringExtra("location");
        bg=i.getStringExtra("bg");

        initViews();

        extractDbData();



    }

    private void extractDbData() {

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot user_child : dataSnapshot.getChildren()) {

                    if(user_child.child("location").getValue().toString().equals(location)&&user_child.child("bloodgroup").getValue().toString().equals(bg)
                            &&user_child.child("donation").getValue().toString().equals("1"))
                    {
                        Users new_user=user_child.getValue(Users.class);

                        users_list.add(new_user);
                    }

                }
                adapter=new users_adapter(users_list,searchResults.this);

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void initViews() {
        recyclerView=findViewById(R.id.search_recycleview);
        linearLayoutManager=new LinearLayoutManager(this);
        users_list=new ArrayList<>();
        myref=FirebaseDatabase.getInstance().getReference("Users");
    }
}
