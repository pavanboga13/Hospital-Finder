package com.example.hospitalfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class ApointmentsList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseConnection mydb;
    ArrayList<String> user_name, user_blood, user_age;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apointments_list);

        recyclerView = findViewById(R.id.apointment_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mydb = new DatabaseConnection(ApointmentsList.this);
        user_name = new ArrayList<>();
        user_blood = new ArrayList<>();
        user_age = new ArrayList<>();

        storeDataInArrays();
        customAdapter = new CustomAdapter(ApointmentsList.this, user_name, user_blood, user_age);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ApointmentsList.this));
    }

    void storeDataInArrays(){
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