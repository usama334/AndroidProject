package com.example.hp.blooddonation;

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

public class my_requests extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    my_requests_adapter adapter;
    ArrayList<my_requests_model_class> contacts;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_requests);
        initViews();

        setContacts();
        adapter=new my_requests_adapter(contacts,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setContacts() {
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("rqt", "requesttable 1");
                for (DataSnapshot user_child : dataSnapshot.getChildren()) {
                    Log.d("rqt", "requesttable 2");

                    my_requests_model_class new_req=user_child.getValue(my_requests_model_class.class);

                        contacts.add(new_req);
                    Log.d("rqt", "requesttable 3");

                }
                adapter=new my_requests_adapter(contacts,my_requests.this);

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void initViews() {
        recyclerView=findViewById(R.id.recycle_view_my_requests);
        linearLayoutManager=new LinearLayoutManager(this);
        contacts=new ArrayList<>();
        myref=FirebaseDatabase.getInstance().getReference("Requests");

    }
}
