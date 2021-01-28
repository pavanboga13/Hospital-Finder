package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminMainMenu extends AppCompatActivity {

    ImageButton imageButton1, imageButton2, imageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_menu);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        imageButton1 = findViewById(R.id.view_appointments);
        imageButton2 = findViewById(R.id.view_customer_review);
        imageButton3 = findViewById(R.id.logout_to_home);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminAppointment.class));
                }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminReviewList.class));
                }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminLogin.class));
                finish();
            }
        });
    }
}