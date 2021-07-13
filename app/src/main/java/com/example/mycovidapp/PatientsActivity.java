package com.example.mycovidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mycovidapp.FragmentPatient.DashboardFragmentPatient;
import com.example.mycovidapp.FragmentPatient.DoctorsFragmentPatient;
import com.example.mycovidapp.FragmentPatient.ResourcesFragmentPatient;
import com.example.mycovidapp.Fragments.DashboardFragment;
import com.example.mycovidapp.Fragments.DoctorsFragment;
import com.example.mycovidapp.Fragments.ResourcesFragment;
import com.example.mycovidapp.databinding.ActivityMainBinding;
import com.example.mycovidapp.databinding.ActivityPatientsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class PatientsActivity extends AppCompatActivity {
    ActivityPatientsBinding binding;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_patient, new DashboardFragmentPatient()).commit();

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.dashboard); // change to whichever id should be default
        }
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                auth.signOut();
                auth.equals(null);
                //Toast.makeText(MainActivity.this, "Logout clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PatientsActivity.this,FirstOpenPage.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.dashboard:
                    selectedFragment = new DashboardFragmentPatient();
                    break;
                case R.id.resources:
                    selectedFragment = new ResourcesFragmentPatient();
                    break;
                case R.id.doctors:
                    selectedFragment = new DoctorsFragmentPatient();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container_patient, selectedFragment)
                    .commit();
            return true;
        }

    };
}