package com.example.mycovidapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
 * Use the {@link ResourcesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourcesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DatabaseReference mDatabase;
    private String Displaycurrvaccine, Displaycurroxygen, DisplaycurrICUbeds, DisplaycurrNbeds, Displaycurrplasma;

    public ResourcesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResourcesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResourcesFragment newInstance(String param1, String param2) {
        ResourcesFragment fragment = new ResourcesFragment();
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

        View root = inflater.inflate(R.layout.fragment_resources, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(user!=null) {

            //Toast.makeText(getActivity(), "fef", Toast.LENGTH_SHORT).show();
            mDatabase.child("Institutes").child(user.getUid()).addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    Displaycurrvaccine=dataSnapshot.child("currVaccine").getValue(String.class);
                    Displaycurroxygen=dataSnapshot.child("currOxygen").getValue(String.class);
                    DisplaycurrICUbeds=dataSnapshot.child("currICUBeds").getValue(String.class);
                    DisplaycurrNbeds=dataSnapshot.child("currNBeds").getValue(String.class);
                    Displaycurrplasma=dataSnapshot.child("currPlasma").getValue(String.class);

                    TextView setCurrVaccine = (TextView) root.findViewById(R.id.currVaccines);
                    setCurrVaccine.setText(Displaycurrvaccine);

                    TextView setCurrOxygen = (TextView) root.findViewById(R.id.currOxygen);
                    setCurrOxygen.setText(Displaycurroxygen);

                    TextView setCurrICUBeds = (TextView) root.findViewById(R.id.currICUBeds);
                    setCurrICUBeds.setText(DisplaycurrICUbeds);

                    TextView setCurrNBeds = (TextView) root.findViewById(R.id.currNBeds);
                    setCurrNBeds.setText(DisplaycurrNbeds);

                    TextView setCurrPlasma = (TextView) root.findViewById(R.id.currPlasma);
                    setCurrPlasma.setText(Displaycurrplasma);

                    Button buttonAddVaccine = (Button) root.findViewById(R.id.addVaccine);
                    buttonAddVaccine.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editVaccine);
                            int addVaccine = Integer.parseInt(mEdit.getText().toString());
                            int currVaccine= Integer.parseInt(Displaycurrvaccine);
                            int value=currVaccine+addVaccine;
                            String totalVaccine=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currVaccine").setValue(totalVaccine);
                        }
                    });

                    Button buttonSubVaccine = (Button) root.findViewById(R.id.subtractVaccine);
                    buttonSubVaccine.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editVaccine);
                            int subVaccine = Integer.parseInt(mEdit.getText().toString());
                            int currVaccine= Integer.parseInt(Displaycurrvaccine);
                            int value=currVaccine-subVaccine;
                            String totalVaccine=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currVaccine").setValue(totalVaccine);
                        }
                    });

                    Button buttonAddOxygen = (Button) root.findViewById(R.id.addOxygen);
                    buttonAddOxygen.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editOxygen);
                            int addOxygen = Integer.parseInt(mEdit.getText().toString());
                            int currOxygen= Integer.parseInt(Displaycurroxygen);
                            int value=currOxygen+addOxygen;
                            String totalOxygen=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currOxygen").setValue(totalOxygen);
                        }
                    });

                    Button buttonSubOxygen = (Button) root.findViewById(R.id.subtractOxygen);
                    buttonSubOxygen.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editOxygen);
                            int subOxygen = Integer.parseInt(mEdit.getText().toString());
                            int currOxygen= Integer.parseInt(Displaycurroxygen);
                            int value=currOxygen-subOxygen;
                            String totalOxygen=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currOxygen").setValue(totalOxygen);
                        }
                    });

                    Button buttonAddICUBeds = (Button) root.findViewById(R.id.addICUBeds);
                    buttonAddICUBeds.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editICUBeds);
                            int addBeds = Integer.parseInt(mEdit.getText().toString());
                            int currBeds= Integer.parseInt(DisplaycurrICUbeds);
                            int value=currBeds+addBeds;
                            String totalBeds=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currICUBeds").setValue(totalBeds);
                        }
                    });

                    Button buttonSubICUBeds = (Button) root.findViewById(R.id.subtractICUBeds);
                    buttonSubICUBeds.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editICUBeds);
                            int subBeds = Integer.parseInt(mEdit.getText().toString());
                            int currBeds= Integer.parseInt(DisplaycurrICUbeds);
                            int value=currBeds-subBeds;
                            String totalBeds=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currICUBeds").setValue(totalBeds);
                        }
                    });

                    Button buttonAddNBeds = (Button) root.findViewById(R.id.addNBeds);
                    buttonAddNBeds.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editNBeds);
                            int addBeds = Integer.parseInt(mEdit.getText().toString());
                            int currBeds= Integer.parseInt(DisplaycurrNbeds);
                            int value=currBeds+addBeds;
                            String totalBeds=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currNBeds").setValue(totalBeds);
                        }
                    });

                    Button buttonSubNBeds = (Button) root.findViewById(R.id.subtractNBeds);
                    buttonSubNBeds.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editNBeds);
                            int subBeds = Integer.parseInt(mEdit.getText().toString());
                            int currBeds= Integer.parseInt(DisplaycurrNbeds);
                            int value=currBeds-subBeds;
                            String totalBeds=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currNBeds").setValue(totalBeds);
                        }
                    });

                    Button buttonAddPlasma = (Button) root.findViewById(R.id.addPlasma);
                    buttonAddPlasma.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editPlasma);
                            int addPlasma = Integer.parseInt(mEdit.getText().toString());
                            int currPlasma= Integer.parseInt(Displaycurrplasma);
                            int value=currPlasma+addPlasma;
                            String totalPlasma=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currPlasma").setValue(totalPlasma);
                        }
                    });

                    Button buttonSubPlasma = (Button) root.findViewById(R.id.subtractPlasma);
                    buttonSubPlasma.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            EditText mEdit = (EditText) root.findViewById(R.id.editPlasma);
                            int subPlasma = Integer.parseInt(mEdit.getText().toString());
                            int currPlasma= Integer.parseInt(Displaycurrplasma);
                            int value=currPlasma-subPlasma;
                            String totalPlasma=Integer.toString(value);
                            mDatabase.child("Institutes").child(user.getUid()).child("currPlasma").setValue(totalPlasma);
                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
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