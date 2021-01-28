package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HospitalMenu extends AppCompatActivity {

    ImageButton locationtomap, database, togocamera, gotoapoinmentlist;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        locationtomap = findViewById(R.id.add_location);
        database = findViewById(R.id.view_database);
        togocamera = findViewById(R.id.open_camera);
        gotoapoinmentlist = findViewById(R.id.apointment_accepted);
        logout = findViewById(R.id.UserLogout);

        locationtomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ApointmentsList.class));
            }
        });

        togocamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Open_camera.class));
            }
        });

        gotoapoinmentlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ApprovedApointmentList.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), User_login.class));
                finish();
            }
        });

    }
}