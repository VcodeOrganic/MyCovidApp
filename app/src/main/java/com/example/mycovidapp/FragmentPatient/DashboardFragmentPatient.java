package com.example.mycovidapp.FragmentPatient;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mycovidapp.R;
import com.example.mycovidapp.SignInInstitute;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragmentPatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragmentPatient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference mDatabase;
    private String Displayname, DisplayMail, DisplayPhone, DisplayPincode, DisplayDOB;

    public DashboardFragmentPatient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragmentPatient.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragmentPatient newInstance(String param1, String param2) {
        DashboardFragmentPatient fragment = new DashboardFragmentPatient();
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
        View root = inflater.inflate(R.layout.fragment_dashboard_patient, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(user!=null){

            mDatabase.child("Users").child(user.getUid()).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Displayname = dataSnapshot.child("userName").getValue(String.class);
                    DisplayMail = dataSnapshot.child("mail").getValue(String.class);
                    DisplayPhone = dataSnapshot.child("phoneNo").getValue(String.class);
                    DisplayPincode = dataSnapshot.child("pincode").getValue(String.class);
                    DisplayDOB = dataSnapshot.child("dob").getValue(String.class);

                    TextView setName = (TextView) root.findViewById(R.id.userName);
                    setName.setText(Displayname);
                    TextView setPhone = (TextView) root.findViewById(R.id.userPhone);
                    setPhone.setText(DisplayPhone);
                    TextView setEmail = (TextView) root.findViewById(R.id.userMail);
                    setEmail.setText(DisplayMail);
                    TextView setPincode = (TextView) root.findViewById(R.id.userPincode);
                    setPincode.setText(DisplayPincode);
                    TextView setDOB = (TextView) root.findViewById(R.id.userDOB);
                    setDOB.setText(DisplayDOB);
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