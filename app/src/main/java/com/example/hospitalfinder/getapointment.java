package com.example.hospitalfinder;

import androidx.annotation.NonNull;
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


public class getapointment extends AppCompatActivity {

    Button gotom, totomenu;
    TextView hosname, addname;
    EditText etext1, etext2, etext3;

    DatabaseConnection mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getapointment);

        hosname = findViewById(R.id.hospital_name);
        addname = findViewById(R.id.hospital_address);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mydb = new DatabaseConnection(this);

        etext1 = findViewById(R.id.name);
        etext2 = findViewById(R.id.bloodgroup);
        etext3 = findViewById(R.id.age);

        gotom = findViewById(R.id.request_apointment);
        totomenu = findViewById(R.id.go_back_to_main_menu);

        String title = getIntent().getStringExtra("title");
        hosname.setText(title);
        String showloca = getIntent().getStringExtra("Location_Address");
        addname.setText(showloca);

        gotom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etext1.getText().toString().trim();
                String userblood = etext2.getText().toString().trim();
                String userage = etext3.getText().toString().trim();
                String hospitalname = hosname.getText().toString();

                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(userblood) && !TextUtils.isEmpty(userage))
                {
                    mydb.dbinsert(username,userblood, Integer.parseInt(userage));
                    Toast.makeText(getapointment.this, "Apointment is send. Please wait for message.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HospitalMenu.class));
                    finish();

                }
                else
                {
                    Toast.makeText(getapointment.this, "All filleds are requeired...", Toast.LENGTH_SHORT).show();

                }

            }
        });


        totomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HospitalMenu.class));
            }
        });


    }
}