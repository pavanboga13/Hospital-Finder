package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_accpet extends AppCompatActivity {

    EditText name_input, blood_input, age_input, app_date, app_time;
    Button accept_btn, cancel_btn;
    String uaaname, uaablood, uaaage;
    DatabaseConnection mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accpet);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        name_input = findViewById(R.id.name2);
        blood_input = findViewById(R.id.bloodgroup2);
        age_input = findViewById(R.id.age2);
        app_date = findViewById(R.id.date);
        app_time = findViewById(R.id.time_for_app);
        accept_btn = findViewById(R.id.Accept_app);
        cancel_btn = findViewById(R.id.Cancel_app);

        mydb = new DatabaseConnection(this);

        name_input.setClickable(false);
        name_input.setEnabled(false);

        blood_input.setClickable(false);
        blood_input.setEnabled(false);

        age_input.setClickable(false);
        age_input.setEnabled(false);

        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name_in = name_input.getText().toString().trim();
                String user_blood_in = blood_input.getText().toString().trim();
                String user_age_in = age_input.getText().toString().trim();
                String user_date_in = app_date.getText().toString().trim();
                String user_time_in = app_time.getText().toString().trim();

                if(!TextUtils.isEmpty(user_name_in) && !TextUtils.isEmpty(user_blood_in) && !TextUtils.isEmpty(user_age_in) && !TextUtils.isEmpty(user_date_in) && !TextUtils.isEmpty(user_time_in))
                {
                    try{
                    mydb.admininsert(user_name_in, user_blood_in, user_age_in, user_date_in, user_time_in);
                    mydb.deleteoldappoinments(user_name_in, user_blood_in, user_age_in);
                    Toast.makeText(Admin_accpet.this, "Appoinment is send.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), AdminMainMenu.class));
                    finish();
                    }
                    catch (Exception e)
                    {
                            Toast.makeText(Admin_accpet.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Admin_accpet.this, "All filleds are requeired...", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name_in = name_input.getText().toString().trim();
                String user_blood_in = blood_input.getText().toString().trim();
                String user_age_in = age_input.getText().toString().trim();
                mydb.deleteoldappoinments(user_name_in, user_blood_in, user_age_in);
                Toast.makeText(Admin_accpet.this, "Appoinment canceled.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), AdminMainMenu.class));
                finish();
            }
        });

        getAndSetIntentData();
    }

    void getAndSetIntentData()
    {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("blood") && getIntent().hasExtra("age"))
        {
            uaaname = getIntent().getStringExtra("name");
            uaablood = getIntent().getStringExtra("blood");
            uaaage = getIntent().getStringExtra("age");

            name_input.setText(uaaname);
            blood_input.setText(uaablood);
            age_input.setText(uaaage);
        }
        else
        {
            Toast.makeText(this, "No data found...", Toast.LENGTH_SHORT).show();
        }
    }
}