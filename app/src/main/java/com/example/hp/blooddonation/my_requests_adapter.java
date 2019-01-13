package com.example.hp.blooddonation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class my_requests_adapter extends RecyclerView.Adapter<my_requests_adapter.Vholder> {
    ArrayList<my_requests_model_class> data;
    Context context;

    // data to be shown is stored in string array , passed to constructor
    public my_requests_adapter(ArrayList<my_requests_model_class> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    // creates views and holds them together
    public Vholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // returns corresponding objects to the views created in layout file
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.row_my_requests,parent,false);
        return new Vholder(view);
    }

    @Override
    // binds views and data together
    public void onBindViewHolder(@NonNull Vholder vholder, int position) {
        my_requests_model_class person= data.get(position);
        vholder.txt_name.setText(person.my_req_name);
        vholder.txt_num.setText(person.my_req_no);
        vholder.txt_title.setText(person.my_req_name.substring(0,1));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class Vholder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_num,txt_title;
        public ImageView img_person;
        public Vholder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.my_requests_name);
            txt_num=itemView.findViewById(R.id.my_requests_number);
            txt_title=itemView.findViewById(R.id.icon_person_name);
        }
    }
}

