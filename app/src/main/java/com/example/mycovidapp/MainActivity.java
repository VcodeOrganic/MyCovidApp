package com.example.mycovidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycovidapp.Fragments.DashboardFragment;
import com.example.mycovidapp.Fragments.DoctorsFragment;
import com.example.mycovidapp.Fragments.ResourcesFragment;
import com.example.mycovidapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActionBar actionBar = getSupportActionBar();
        TextView tv = new TextView(getApplicationContext());
        Typeface typeface = ResourcesCompat.getFont(this, R.font.abeezee);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        tv.setLayoutParams(lp);
        tv.setText("MyCovidApp"); // ActionBar title text
        tv.setTextSize(18);
        tv.setTextColor(Color.WHITE);
        tv.setTypeface(typeface);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(tv);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();

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
                Intent intent = new Intent(MainActivity.this,FirstOpenPage.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    //testing new remote

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.dashboard:
                    selectedFragment = new DashboardFragment();
                    break;
                case R.id.resources:
                    selectedFragment = new ResourcesFragment();
                    break;
                case R.id.doctors:
                    selectedFragment = new DoctorsFragment();
                    break;
            }
            // It will help to replace the
            // one fragment to other.
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }

    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
}