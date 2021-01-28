package com.example.hospitalfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.http.util.TextUtils;

import java.util.HashMap;
import java.util.Map;

public class AdminRegistration extends AppCompatActivity {

    TextView view1, view2, view3, view4, view5;
    Button button1;
    FirebaseFirestore fstoreadmin;
    FirebaseAuth fauthadmin;
    ProgressBar fProcess;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fstoreadmin = FirebaseFirestore.getInstance();
        fauthadmin = FirebaseAuth.getInstance();
        view1 = findViewById(R.id.admin_name);
        view2 = findViewById(R.id.admin_hospital_name);
        view3 = findViewById(R.id.admin_mobile_number);
        view4 = findViewById(R.id.admin_password);
        view5 = findViewById(R.id.admin_password_confer);
        button1 = findViewById(R.id.admin_Confirm);
        fProcess = findViewById(R.id.processBaeforshowuser2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = view1.getText().toString().trim();
                final String email = view2.getText().toString().trim();
                final String mobile = view3.getText().toString().trim();
                final String password = view4.getText().toString().trim();
                final String conpass = view5.getText().toString().trim();

                if (TextUtils.isEmpty(name))
                {
                    Toast.makeText(AdminRegistration.this, "Enter Hospital Name...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(AdminRegistration.this, "Enter Hospital emailid...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mobile) || mobile.length()<10)
                {
                    Toast.makeText(AdminRegistration.this, "Valid Mobile Number is required...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Toast.makeText(AdminRegistration.this, "Enter Password...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6)
                {
                    Toast.makeText(AdminRegistration.this, "Password must be greater than 7 characters...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(conpass))
                {
                    Toast.makeText(AdminRegistration.this,"Please re-enter correct password",Toast.LENGTH_LONG).show();
                    view4.setText("");
                    view5.setText("");
                    fProcess.setVisibility(View.INVISIBLE);
                    return;
                }
                fProcess.setVisibility(View.VISIBLE);

                fauthadmin.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            userID = fauthadmin.getCurrentUser().getUid();
                            DocumentReference documentReference = fstoreadmin.collection("AdminData").document(userID);
                            Map<String, Object> adminuser = new HashMap<>();
                            adminuser.put("Name", name);
                            adminuser.put("Email", email);
                            adminuser.put("MobileNo", mobile);
                            adminuser.put("Password", password);
                            documentReference.set(adminuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AdminRegistration.this, "Admin Created Successfully...", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), AdminLogin.class));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AdminRegistration.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                        else
                        {
                            fProcess.setVisibility(View.GONE);
                            Toast.makeText(AdminRegistration.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}