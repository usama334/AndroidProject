package com.example.hp.blooddonation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_up extends AppCompatActivity {


    private EditText inputEmail, inputPassword,inputName,inputLoc,inputNum;     //hit option + enter if you on mac , for windows hit ctrl + enter
    private Button  btnSignUp;
    private TextView btnSignIn;
    private Spinner  inputGender,inputBG,inputDonation;
    private ProgressBar progressBar;
    FirebaseAuth user_auth;

    DatabaseReference path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initVars();

//        btnResetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(sign_up.this, forgot_password.class));
//            }
//        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i=new Intent(sign_up.this,login.class);
               startActivity(i);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String name=inputName.getText().toString().trim();
                String contact=inputNum.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), "Enter Your name!", Toast.LENGTH_LONG).show();

                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_LONG).show();

                    return;
                }


                if(!isEmailValid(email)){
                    Toast.makeText(sign_up.this, "Enter a valid email address", Toast.LENGTH_LONG).show();
                }

                if (TextUtils.isEmpty(password)) {
                    Log.d("sign", "onclick function me agya step 3 ");
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length() < 8) {

                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 8 characters!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(contact)) {
                    Toast.makeText(getApplicationContext(), "Enter Your contact number!", Toast.LENGTH_LONG).show();

                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                user_auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(sign_up.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(sign_up.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(sign_up.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    addusers();
                                    startActivity(new Intent(sign_up.this, nav.class));
                                    finish();
                                }
                            }
                        });




            }
        });
    }
    private  void initVars(){
        user_auth=FirebaseAuth.getInstance();
        btnSignIn = (TextView) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputBG=(Spinner) findViewById(R.id.spn_blood_group);
        inputGender=(Spinner) findViewById(R.id.spn_gender);
        inputLoc=(EditText)findViewById(R.id.edt_location);
        inputName=(EditText)findViewById(R.id.edt_name);
        inputNum=(EditText)findViewById(R.id.edt_number);
        inputDonation=(Spinner)findViewById(R.id.spn_donor);
        path=FirebaseDatabase.getInstance().getReference().child("Users");


        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //    btnResetPassword = (Button) findViewById(R.id.btn_reset_password);


    }

    private void addusers() {
        String user_name,user_gender,user_loc,user_bg,user_email,user_no,user_pass;
        String donation;


        user_name=inputName.getText().toString();
        user_bg=inputBG.getSelectedItem().toString();
        user_email=inputEmail.getText().toString();
        user_loc=inputLoc.getText().toString();
        user_gender=inputGender.getSelectedItem().toString();
        user_no=inputNum.getText().toString();
        user_pass=inputPassword.getText().toString();
        donation=inputDonation.getSelectedItem().toString();

        Users myuser;

        if (donation.equals("Yes")){
            myuser=new Users(user_name,user_email,user_pass,user_gender,user_loc,user_bg,user_no,1);
        }
        else {
            myuser=new Users(user_name,user_email,user_pass,user_gender,user_loc,user_bg,user_no,0);
        }

        path.push().setValue(myuser);
        Toast.makeText(sign_up.this, donation, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
    public static boolean isEmailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
