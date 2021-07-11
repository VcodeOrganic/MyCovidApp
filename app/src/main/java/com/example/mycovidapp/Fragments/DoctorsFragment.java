package com.example.mycovidapp.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycovidapp.Models.Doctors;
import com.example.mycovidapp.R;
import com.example.mycovidapp.SignInInstitute;
import com.example.mycovidapp.SignUpActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference mDatabase;
    //private static int AlltimeDocCount = 0;
    private String currDocEntry;
    public String sDocInsti;
    public String sDocPhone, sDocName, sDocCH;

    public DoctorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorsFragment newInstance(String param1, String param2) {
        DoctorsFragment fragment = new DoctorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_doctors, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(user!=null){
            FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDatabase.child("Institutes").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            sDocInsti = dataSnapshot.child("instiName").getValue(String.class);

                            EditText mDocName = (EditText) root.findViewById(R.id.etDocname);
                            EditText mDocPhone = (EditText) root.findViewById(R.id.etDocPhone);
                            EditText mDocCH = (EditText) root.findViewById(R.id.etDocCallingHours);

                            sDocName = mDocName.getText().toString();
                            sDocPhone = mDocPhone.getText().toString();
                            sDocCH = mDocCH.getText().toString();

                            Doctors doctor = new Doctors(sDocName,sDocPhone, sDocInsti,sDocCH);
                            Random ran = new Random();
                            int code = ran.nextInt(10000);
                            currDocEntry = "Doc" + String.valueOf(code);

                            mDatabase.child("Doctors").child(currDocEntry).setValue(doctor);
                            Toast.makeText(getActivity(), "New Doctor has been added successfully!", Toast.LENGTH_SHORT).show();

                            //FragmentTransaction ft = getFragmentManager().beginTransaction();
                            //ft.detach(DoctorsFragment.this).attach(DoctorsFragment.this).commit();
                            //DoctorsFragment fragmentInstance= new DoctorsFragment(); getSupport().beginTransaction().remove(fragmentInstance).commit();
                            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}
                    });

                }
            });
        }
        else{
            Intent intent = new Intent(getActivity(), SignInInstitute.class);
            startActivity(intent);
        }

        return root;
    }
}