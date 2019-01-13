package com.example.hp.blooddonation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth user_auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin,btnReset;
  //  DatabaseReference path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Get Firebase auth instance
        initVars();
        user_auth = FirebaseAuth.getInstance();

//        if (user_auth.getCurrentUser() != null) {
//            startActivity(new Intent(login.this, blood_request.class));
//            finish();
//        }

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, forgot_password.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,sign_up.class);
                startActivity(i);
            }
        });


//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginUser(inputEmail.getText().toString(),inputPassword.getText().toString());
//                Toast.makeText(login.this, "Hello g", Toast.LENGTH_SHORT).show();
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                Toast.makeText(login.this, "tsting", Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                user_auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 8) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(login.this, nav.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });
    }

//    private void loginUser(final String email, final String password) {
//
//
//        user.addValueEventListener(new ValueEventListener() {
//            int flag=0;
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                for (DataSnapshot child_email : dataSnapshot.getChildren()) {
//
//                    if(email.equals(child_email.child("user_email").getValue().toString())){
//                        flag=1;
//                        if(password.equals(child_email.child("user_password").getValue().toString())){
//                            Intent i=new Intent(login.this,blood_request.class);
//                            startActivity(i);
//                        }
//                        else{
//                            Toast.makeText(login.this, "Password Is wrong", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                    }
//
//                }
//                if(flag==0){
//                    Toast.makeText(login.this, "Email is Wrong", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }


    public void initVars(){
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset=(Button)findViewById(R.id.btn_reset_password);
        user_auth=FirebaseAuth.getInstance();
      //  btnReset = (Button) findViewById(R.id.btn_reset_password);
    //    path=FirebaseDatabase.getInstance().getReference().child("Users");

    }
}
