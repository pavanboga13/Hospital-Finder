package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminAppointment extends AppCompatActivity{

    RecyclerView recyclerView;
    DatabaseConnection mydb;
    ArrayList<String> user_name, user_blood, user_age;
    AcminCustomAdapter adminApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appointment);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recyclerView = findViewById(R.id.app_show);

        mydb = new DatabaseConnection(AdminAppointment.this);
        user_name = new ArrayList<>();
        user_blood = new ArrayList<>();
        user_age = new ArrayList<>();

        storeDataInArrays();
        adminApp = new AcminCustomAdapter(AdminAppointment.this, user_name, user_blood, user_age);
        recyclerView.setAdapter(adminApp);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminAppointment.this));

    }

    private void storeDataInArrays() {
        Cursor cursor = mydb.readAllApointments();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                user_name.add(cursor.getString(0));
                user_blood.add(cursor.getString(1));
                user_age.add(cursor.getString(2));
            }
        }
    }


}