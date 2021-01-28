package com.example.hospitalfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    TextView view1, view2;
    Button button1, button2, button3;
    ProgressBar progressBar;
    FirebaseAuth fauthdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        view1 = findViewById(R.id.admin_name);
        view2 = findViewById(R.id.admin_pass_con);
        button1 = findViewById(R.id.btn_login_to_database);
        button2 = findViewById(R.id.btn_cancel_to_database);
        button3 = findViewById(R.id.togouserPage);
        progressBar = findViewById(R.id.progressBarInLogin);

        fauthdata = FirebaseAuth.getInstance();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminemail = view1.getText().toString().trim();
                String adminpassword = view2.getText().toString().trim();

                if (!TextUtils.isEmpty(adminemail ) && !TextUtils.isEmpty(adminpassword))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    fauthdata.signInWithEmailAndPassword(adminemail, adminpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(AdminLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), AdminMainMenu.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(AdminLogin.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(AdminLogin.this, "Fill all ", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }



            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminRegistration.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), User_login.class));
                finish();
            }
        });

    }
}