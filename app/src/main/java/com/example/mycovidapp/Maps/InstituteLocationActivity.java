package com.example.mycovidapp.Maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mycovidapp.R;
import com.example.mycovidapp.SignInInstitute;
import com.example.mycovidapp.SignUpInstitute;
import com.example.mycovidapp.databinding.ActivityInstituteLocationBinding;

public class InstituteLocationActivity extends AppCompatActivity {

    ActivityInstituteLocationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInstituteLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPlacePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstituteLocationActivity.this, MainMaps.class);
                startActivity(intent);
                //PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

            }
        });

        binding.etExistingInstiLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InstituteLocationActivity.this, SignInInstitute.class);
                startActivity(intent);
            }
        });
    }


}