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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class User_login extends AppCompatActivity {

    EditText mEamil, mPassword;
    Button mLoginto, admintowait;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mEamil = findViewById(R.id.editTextTextPersonName);
        mPassword = findViewById(R.id.editTextTextPersonName3);
        progressBar = findViewById(R.id.progressBarInLogin);
        mLoginto = findViewById(R.id.btnlog1);
        admintowait = findViewById(R.id.goto_admin_panel);

        fAuth = FirebaseAuth.getInstance();

        mLoginto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEamil.getText().toString().trim();
                String pass = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(User_login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass))
                {
                    Toast.makeText(User_login.this, "Password Missing", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.length() < 6)
                {
                    Toast.makeText(User_login.this, "Re-Enter the Password", Toast.LENGTH_SHORT).show();
                    mPassword.setText("");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(User_login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HospitalMenu.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(User_login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        Button registerbtn1 = (Button) findViewById(R.id.registerbtn1);
        registerbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_login.this, User_Register.class));
            }
        });

        admintowait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminLogin.class));
            }
        });
    }
}