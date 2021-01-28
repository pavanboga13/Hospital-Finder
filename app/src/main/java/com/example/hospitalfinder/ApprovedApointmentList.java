package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ApprovedApointmentList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseConnection mydb;
    ArrayList<String> my_name, my_blood, my_age, my_date, my_time;
    AppCustomAdapter appCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approved_apointment_list);

        recyclerView = findViewById(R.id.app_data_in_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mydb = new DatabaseConnection(ApprovedApointmentList.this);
        my_name = new ArrayList<>();
        my_blood = new ArrayList<>();
        my_age = new ArrayList<>();
        my_date = new ArrayList<>();
        my_time = new ArrayList<>();

        storeDataInButtonArrays();
        appCustomAdapter = new AppCustomAdapter(ApprovedApointmentList.this, my_name, my_blood, my_age, my_date, my_time);
        recyclerView.setAdapter(appCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ApprovedApointmentList.this));
    }


    void storeDataInButtonArrays(){
        Cursor cursor = mydb.readAllAppApointments();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                my_name.add(cursor.getString(0));
                my_blood.add(cursor.getString(1));
                my_age.add(cursor.getString(2));
                my_date.add(cursor.getString(3));
                my_time.add(cursor.getString(4));
            }
        }
    }




}