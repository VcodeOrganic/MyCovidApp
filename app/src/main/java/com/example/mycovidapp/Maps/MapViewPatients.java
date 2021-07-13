package com.example.mycovidapp.Maps;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.mycovidapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.Map;

public class MapViewPatients extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng>mapList = new ArrayList<LatLng>();
    ArrayList<String>nameList = new ArrayList<String>();
    private DatabaseReference mDatabaseRef;

    LatLng sydney = new LatLng(-34,151);
    LatLng brisbane = new LatLng(-27.470125,153.021072);

    String m1 = "Sydney";
    String m2 = "Brisbane";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view_patients);



        mapList.add(sydney);
        mapList.add(brisbane);
        nameList.add(m1);
        nameList.add(m2);



        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_patient);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Institutes");

        Query mQuery= mDatabaseRef;
        mQuery.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                Map<String,Object> value = (Map<String, Object>) dataSnapshot.getValue();
                String latitude = String.valueOf(value.get("myLatitude"));
                String longitude = String.valueOf(value.get("myLongitude"));

                String name= String.valueOf(value.get("instiName"));
                double  lati = Double.parseDouble(latitude);
                double  longi = Double.parseDouble(longitude);
                LatLng location = new LatLng(lati, longi);
                mapList.add(location);
                nameList.add(name);

                mMap.addMarker(new MarkerOptions()
                        .position(location)
                        .title(name));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

//
//        for(int i=0;i< mapList.size();i++){
//            Log.d("DEBuuuu",mapList.get(i)+"");
//            mMap.addMarker(new MarkerOptions().position(mapList.get(i)).title(nameList.get(i)));
//            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(mapList.get(i)));
//
//        }

//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}