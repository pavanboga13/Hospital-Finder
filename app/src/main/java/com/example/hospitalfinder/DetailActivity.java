package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class DetailActivity extends AppCompatActivity {

    TextView view1, v1;
    Button btapoint, btreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        view1 = findViewById(R.id.show_intent_name);
        v1 = findViewById(R.id.Show_Address);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btapoint = findViewById(R.id.go_to_take_apointment);
        btreview = findViewById(R.id.give_review_to_hospital);

        String title = getIntent().getStringExtra("title");
        view1.setText(title);
        String showloca = getIntent().getStringExtra("Location_Address");
        v1.setText(showloca);

        btapoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data1 = view1.getText().toString();
                String data2 = v1.getText().toString();
                Intent intent = new Intent(getApplicationContext(), getapointment.class);
                intent.putExtra("title",data1);
                intent.putExtra("Location_Address", data2);
                startActivity(intent);
            }
        });

        btreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = view1.getText().toString();
                String data2 = v1.getText().toString();
                Intent intent = new Intent(getApplicationContext(), givereview.class);
                intent.putExtra("title",data1);
                intent.putExtra("Location_Address", data2);
                startActivity(intent);

            }
        });




    }
}