package com.example.hospitalfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class User_Register extends AppCompatActivity {

    EditText fUserID, fUserName, fUserMobile, fUserEmail, fUserPass, fUserConPass;
    Button fSubmitBtn;
    ProgressBar fProcess;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__register);
        fUserID = findViewById(R.id.editTextTextPersonName);
        fUserName = findViewById(R.id.editTextTextPersonName2);
        fUserMobile = findViewById(R.id.editTextNumber);
        fUserEmail = findViewById(R.id.editTextTextEmailAddress);
        fUserPass = findViewById(R.id.editTextTextPersonName3);
        fUserConPass = findViewById(R.id.editTextTextPersonName4);
        fSubmitBtn = findViewById(R.id.buttonSubmit);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        fProcess = findViewById(R.id.processBaeforshowuser);

        fSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String useemail = fUserEmail.getText().toString().trim();
                final String usepass = fUserPass.getText().toString().trim();
                final String useID = fUserID.getText().toString().trim();
                final String usename = fUserName.getText().toString().trim();
                final String usemobile = fUserMobile.getText().toString().trim();
                final String useconpass = fUserConPass.getText().toString().trim();

                if(TextUtils.isEmpty(useemail))
                {
                    Toast.makeText(User_Register.this, "Email is required...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(usepass))
                {
                    Toast.makeText(User_Register.this, "Password is required...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(useID))
                {
                    Toast.makeText(User_Register.this, "User ID is required...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(usemobile) || usemobile.length()<10)
                {
                    Toast.makeText(User_Register.this, "Valid Mobile Number is required...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(usename))
                {
                    Toast.makeText(User_Register.this, "User Name is required...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (usepass.length() < 6)
                {
                    Toast.makeText(User_Register.this, "Password must be greater than 7 characters...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!usepass.equals(useconpass))
                {
                    Toast.makeText(User_Register.this,"Please re-enter correct password",Toast.LENGTH_LONG).show();
                    fUserConPass.setText("");fUserPass.setText("");
                    fProcess.setVisibility(View.INVISIBLE);
                    return;
                }

                fProcess.setVisibility(View.VISIBLE);

                //// Resigration


                fAuth.createUserWithEmailAndPassword(useemail, usepass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(User_Register.this, "User Created Successfully...", Toast.LENGTH_SHORT).show();

                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("UsersData").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Email", useemail);
                            user.put("MobileNo", usemobile);
                            user.put("Name", usename);
                            user.put("Password", usepass);
                            user.put("UserID", useID);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), User_login.class));
                        }
                        else
                        {
                            fProcess.setVisibility(View.GONE);
                            Toast.makeText(User_Register.this, "Error !" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });
    }

}