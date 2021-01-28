package com.example.hospitalfinder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class givereview extends AppCompatActivity {

    Button gotomenu, can;
    TextView namehospitla, addressname;
    EditText multi1, multi2;
    DatabaseConnection mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_givereview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        gotomenu = findViewById(R.id.feedback);
        can = findViewById(R.id.goback);
        namehospitla = findViewById(R.id.hospital_address2);
        addressname = findViewById(R.id.hospital_name2);
        multi1 = findViewById(R.id.editTextTextMultiLine);
        multi2 = findViewById(R.id.editTextTextMultiLine2);

        mydb =new DatabaseConnection(this);

        String title = getIntent().getStringExtra("title");
        namehospitla.setText(title);
        String showloca = getIntent().getStringExtra("Location_Address");
        addressname.setText(showloca);


        gotomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info1 = multi1.getText().toString().trim();
                String info2 = multi2.getText().toString().trim();

                if (!TextUtils.isEmpty(info1) && !TextUtils.isEmpty(info2))
                {
                    mydb.reviewinsert(info1, info2);
                     Toast.makeText(givereview.this, "Thank you for for review...", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(getApplicationContext(), HospitalMenu.class));
                     finish();
                }
                else
                {
                    Toast.makeText(givereview.this, "Give answer of two question...", Toast.LENGTH_SHORT).show();
                }
               }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HospitalMenu.class));
                finish();
            }
        });
    }
}