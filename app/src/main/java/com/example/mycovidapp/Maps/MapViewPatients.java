package com.example.mycovidapp.Maps;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.mycovidapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapViewPatients extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng>arrayList = new ArrayList<LatLng>();
    ArrayList<String>nameList = new ArrayList<String>();

    LatLng sydney = new LatLng(-34,151);
    LatLng brisbane = new LatLng(-27.470125,153.021072);

    String m1 = "Sydney";
    String m2 = "Brisbane";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view_patients);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_patient);
        mapFragment.getMapAsync(this);

        arrayList.add(sydney);
        arrayList.add(brisbane);
        nameList.add(m1);
        nameList.add(m2);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i=0;i< arrayList.size();i++){
            mMap.addMarker(new MarkerOptions().position(arrayList.get(i)).title(nameList.get(i)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));

        }

//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}