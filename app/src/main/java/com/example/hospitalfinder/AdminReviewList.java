package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminReviewList extends AppCompatActivity {

    RecyclerView reviewlist;
    DatabaseConnection mydb;
    ArrayList<String> user_ans1, user_ans2;
    CustomerReviewList customerReviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_review_list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        reviewlist = findViewById(R.id.review_list);
        mydb = new DatabaseConnection(AdminReviewList.this);
        user_ans1 = new ArrayList<>();
        user_ans2 = new ArrayList<>();

        readdatafromreview();
        customerReviewList = new CustomerReviewList(AdminReviewList.this, user_ans1, user_ans2);
        reviewlist.setAdapter(customerReviewList);
        reviewlist.setLayoutManager(new LinearLayoutManager(AdminReviewList.this));
    }

    void readdatafromreview(){
        Cursor cursor = mydb.readAllreview();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No Data...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                user_ans1.add(cursor.getString(0));
                user_ans2.add(cursor.getString(1));
            }
        }
    }

}