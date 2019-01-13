package com.example.hp.blooddonation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class blood_request extends AppCompatActivity {
    EditText edt_req_name, edt_req_hospital, edt_req_no,edt_req_amount,edt_req_city,edt_req_date,edt_req_msg;
    Spinner  edt_req_bg,edt_req_gender;
    DatePickerDialog picker;
    Button submit;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);
        initVars();
        edt_req_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(blood_request.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                edt_req_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDbValues();
                Toast.makeText(blood_request.this, "Request Submitted", Toast.LENGTH_LONG).show();
//                Intent i=new Intent(blood_request.this,nav.class);
//                return;
            }
        });
    }

    private void setDbValues() {
        String name,hospital,req_no,req_amount,req_city,req_date,req_msg,req_bg,req_gender;
        name=edt_req_name.getText().toString();
        hospital=edt_req_hospital.getText().toString();
        req_amount=edt_req_amount.getText().toString();
        req_city=edt_req_city.getText().toString();
        req_date=edt_req_date.getText().toString();
        req_msg=edt_req_msg.getText().toString();
        req_bg=edt_req_bg.getSelectedItem().toString();
        req_no=edt_req_no.getText().toString();
        req_gender=edt_req_gender.getSelectedItem().toString();
        my_requests_model_class request=new my_requests_model_class(name,req_no,hospital,req_city,req_bg,req_msg,req_date);
        myref.push().setValue(request);
        Log.w("requestss", "dbvalues set");
    }

    private void initVars() {
        edt_req_name=(EditText)findViewById(R.id.req_name);
        edt_req_amount=(EditText)findViewById(R.id.req_quantity);
        edt_req_hospital=(EditText)findViewById(R.id.req_hospital);
        edt_req_city=(EditText)findViewById(R.id.req_city);
        edt_req_msg=(EditText)findViewById(R.id.req_msg);
        edt_req_no=(EditText)findViewById(R.id.req_contact);
        edt_req_bg=(Spinner) findViewById(R.id.req_bg);
        edt_req_date=findViewById(R.id.req_date);
        edt_req_date.setInputType(InputType.TYPE_NULL);
        edt_req_gender=findViewById(R.id.req_bg);
        submit=findViewById(R.id.btn_submit);
        myref=FirebaseDatabase.getInstance().getReference("Requests");
    }

}
