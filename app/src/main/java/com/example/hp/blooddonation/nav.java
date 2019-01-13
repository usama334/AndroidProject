package com.example.hp.blooddonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class nav extends AppCompatActivity {
    FirebaseAuth user_auth;
    EditText search_loc;
    Spinner search_bg;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    FirebaseUser user;
    NavigationView navigationView;
    Button btnSearch, btnmyProfile, btnContactUs, btnBloodReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initViews();
        user=FirebaseAuth.getInstance().getCurrentUser();

        user_auth = FirebaseAuth.getInstance();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent i = new Intent(nav.this, login.class);
            startActivity(i);
            return;
        }


        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.d("slitem", "onNavigationItemSelected: "); // doesnot even shows log..
                Toast.makeText(getApplicationContext(), "Please Take a look", Toast.LENGTH_SHORT).show();
                int id = menuItem.getItemId();

                if (id == R.id.nav_myProfile) {

                    Intent i = new Intent(nav.this, myProfile.class);
                    startActivity(i);

                } else if (id == R.id.nav_reqBlood) {
                    Intent i = new Intent(nav.this, blood_request.class);
                    startActivity(i);

                } else if (id == R.id.nav_requests) {
                    Intent i = new Intent(nav.this, my_requests.class);
                    startActivity(i);
                } else if (id == R.id.nav_privacy) {
//                i=new Intent(nav.this,privacy.class);
                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_info) {
                    Intent i = new Intent(nav.this, info.class);
                    startActivity(i);
                } else if (id == R.id.nav_contactUs) {
                    Intent i = new Intent(nav.this, contact_us.class);
                    startActivity(i);

                }
                else if (id == R.id.nav_logout) {
                        FirebaseAuth.getInstance().signOut();
                        Intent i=new Intent(nav.this,login.class);
                        startActivity(i);



                }
                return true;
            }
        });



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(nav.this, searchResults.class);
                i.putExtra("location", search_loc.getText().toString());
                i.putExtra("bg", search_bg.getSelectedItem().toString());
                startActivity(i);
            }
        });
    }

    private void initViews() {
        btnSearch = findViewById(R.id.btn_search);

        search_loc = findViewById(R.id.edt_search_location);
        search_bg = findViewById(R.id.edt_search_group);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}
