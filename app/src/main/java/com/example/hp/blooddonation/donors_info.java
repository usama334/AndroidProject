package com.example.hp.blooddonation;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class donors_info extends AppCompatActivity {

    String donor_name,donor_city,donor_bg,donor_gender,donor_contact,donor_email;
    TextView d_name,d_email,d_city,d_bg,d_contact,d_gender;
    Button btn_dial,btn_goBack,btn_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_info);

        initViews();
        setValues();

        btn_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(donors_info.this,searchResults.class);
                startActivity(i);
            }
        });
        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + donor_contact);

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                startActivity(i);
            }
        });
        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I =new Intent(Intent.ACTION_VIEW);


                try
                {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(donor_contact, null, "Message content goes here", null, null);
                }
                catch(Exception e)
                {
                    Toast.makeText(donors_info.this,"Sms not send",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setValues() {
        d_name.setText(donor_name);
        d_email.setText(donor_email);
        d_gender.setText(donor_gender);
        d_contact.setText(donor_contact);
        d_bg.setText(donor_bg);
        d_city.setText(donor_city);
    }

    private void initViews() {
        Intent i=getIntent();
        donor_name=i.getStringExtra("donor_name");
        donor_email=i.getStringExtra("donor_email");
        donor_city=i.getStringExtra("donor_city");
        donor_bg=i.getStringExtra("donor_bg");
        donor_gender=i.getStringExtra("donor_gender");
        donor_contact=i.getStringExtra("donor_number");

        d_name=findViewById(R.id.txt_donor_name);
        d_email=findViewById(R.id.txt_donor_email);
        d_gender=findViewById(R.id.txt_donor_gender);
        d_city=findViewById(R.id.txt_donor_city);
        d_contact=findViewById(R.id.txt_donor_number);
        d_bg=findViewById(R.id.txt_donor_bgroup);

        btn_dial=findViewById(R.id.btn_contact_donor);
        btn_goBack=findViewById(R.id.btn_goBack);
        btn_msg=findViewById(R.id.btn_msg_donor);



    }



}
