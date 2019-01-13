package com.example.hp.blooddonation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class users_adapter extends RecyclerView.Adapter<users_adapter.VHolder> {
    ArrayList<Users> users_list;
    LinearLayout us;
    Context context;
    DatabaseReference myRef;
    Users single_user;

    public users_adapter(ArrayList<Users> users_list, Context context) {
        this.users_list = users_list;
        this.context = context;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_my_requests, parent, false);
        return new VHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vholder, final int position) {
        single_user = users_list.get(position);
        vholder.txt_name.setText(single_user.user_name);

        vholder.txt_num.setText(single_user.user_contact);
        vholder.txt_title.setText(single_user.user_name.substring(0, 1));
        vholder.rowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, donors_info.class);
//            Bundle bundle=new Bundle();
                Log.d("userrr", users_list.get(position).getUser_name());
                i.putExtra("donor_name", users_list.get(position).getUser_name());
                i.putExtra("donor_email", users_list.get(position).getUser_email());
                i.putExtra("donor_number", users_list.get(position).user_contact);
                i.putExtra("donor_gender", users_list.get(position).getUser_gender());
                i.putExtra("donor_city", users_list.get(position).getLocation());
                i.putExtra("donor_bg", users_list.get(position).getBloodgroup());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users_list.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_num, txt_title;
        public LinearLayout rowLayout;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.my_requests_name);
            txt_num = itemView.findViewById(R.id.my_requests_number);
            txt_title = itemView.findViewById(R.id.icon_person_name);
            rowLayout = itemView.findViewById(R.id.usama);

        }



    }
}
