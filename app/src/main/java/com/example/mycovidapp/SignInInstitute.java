package com.example.mycovidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mycovidapp.databinding.ActivitySignInInstituteBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInInstitute extends AppCompatActivity {

    ActivitySignInInstituteBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInInstituteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(SignInInstitute.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Getting your account ready");

        binding.btnLoginInsti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                auth.signInWithEmailAndPassword
                        (binding.etInstiEmail.getText().toString(),binding.etInstiPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Intent intent = new Intent(SignInInstitute.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(SignInInstitute.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        binding.etNonExistingInsti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInInstitute.this,SignUpInstitute.class);
                startActivity(intent);
            }
        });
        if(auth.getCurrentUser()!= null){
            Intent intent = new Intent(SignInInstitute.this, MainActivity.class);
            startActivity(intent);
        }

    }
}