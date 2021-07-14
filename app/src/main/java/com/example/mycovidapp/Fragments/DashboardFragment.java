package com.example.mycovidapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycovidapp.MainActivity;
import com.example.mycovidapp.Models.Institutes;
import com.example.mycovidapp.R;
import com.example.mycovidapp.SignInInstitute;
import com.example.mycovidapp.VerificationInsti.VerificationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference mDatabase;
    private String Displayname, DisplayAddress, DisplayMail, DisplayPhone, DisplayPincode, DisplayKYCStatus;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(user!=null){

            Button mVerifyButton = (Button) root.findViewById(R.id.btnVerify);
            mVerifyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), VerificationActivity.class);
                    startActivity(intent);
                }
            });
            mDatabase.child("Institutes").child(user.getUid()).addValueEventListener(new ValueEventListener() {

                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d("firebase1", "Name2: ");
                    Displayname = dataSnapshot.child("instiName").getValue(String.class);
                    DisplayAddress = dataSnapshot.child("instiAddress").getValue(String.class);
                    DisplayMail = dataSnapshot.child("instimail").getValue(String.class);
                    DisplayPhone = dataSnapshot.child("instiphone").getValue(String.class);
                    DisplayPincode = dataSnapshot.child("instipincode").getValue(String.class);
                    DisplayKYCStatus = dataSnapshot.child("kycStatus").getValue(String.class);

                    TextView setName = (TextView) root.findViewById(R.id.textName);
                    setName.setText(Displayname);

                    TextView setKYC = (TextView) root.findViewById(R.id.textKYC);
                    setKYC.setText(DisplayKYCStatus);

                    ImageView KYCImage = (ImageView) root.findViewById(R.id.imageKYC);

                    if(DisplayKYCStatus.equals("NO") || DisplayKYCStatus.equals("PENDING")){
                        KYCImage.setImageResource(R.drawable.warning);
                        mVerifyButton.setVisibility(View.VISIBLE);
                    }
                    else{
                        KYCImage.setImageResource(R.drawable.correct_svg);
                        //mVerifyButton.setEnabled(false);
                        mVerifyButton.setVisibility(View.GONE);
                        //mVerifyButton.setText("Verified");
                    }

                    TextView setPhone = (TextView) root.findViewById(R.id.textPhone);
                    setPhone.setText(DisplayPhone);
                    TextView setEmail = (TextView) root.findViewById(R.id.textEmail);
                    setEmail.setText(DisplayMail);
                    TextView setAddress = (TextView) root.findViewById(R.id.textAddress);
                    setAddress.setText(DisplayAddress);
                    TextView setPincode = (TextView) root.findViewById(R.id.textPincode);
                    setPincode.setText(DisplayPincode);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            });


        }
        else{
            Intent intent = new Intent(getActivity(), SignInInstitute.class);
            startActivity(intent);
        }

        return root;
    }
}