package com.example.mycovidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mycovidapp.databinding.ActivityFirstOpenPageBinding;
import com.example.mycovidapp.databinding.ActivitySignUpBinding;

public class FirstOpenPage extends AppCompatActivity {
    ActivityFirstOpenPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstOpenPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.btnJoinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstOpenPage.this,SignUpInstitute.class);
                startActivity(intent);
            }
        });

        binding.btnAvailServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstOpenPage.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}