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
import android.widget.Toast;

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
                    Displaycurrplasma=dataSnapshot.child("currPlasma").getValue(String.class);

                    TextView setCurrVaccine = (TextView) root.findViewById(R.id.currVaccines);
                    setCurrVaccine.setText(Displaycurrvaccine);

                    TextView setCurrOxygen = (TextView) root.findViewById(R.id.currOxygen);
                    setCurrOxygen.setText(Displaycurroxygen);

                    TextView setCurrICUBeds = (TextView) root.findViewById(R.id.currICUBeds);
                    setCurrICUBeds.setText(DisplaycurrICUbeds);

                    TextView setCurrPlasma = (TextView) root.findViewById(R.id.currPlasma);
                    setCurrPlasma.setText(Displaycurrplasma);
                    EditText mEditVaccine = (EditText) root.findViewById(R.id.editVaccine);
                    Button buttonAddVaccine = (Button) root.findViewById(R.id.addVaccine);
                    buttonAddVaccine.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int addVaccine = 0;
                            try {
                                addVaccine = Integer.parseInt(mEditVaccine.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currVaccine= Integer.parseInt(Displaycurrvaccine);
                            int value=currVaccine+addVaccine;
                            if(value>0) {
                                String totalVaccine = Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currVaccine").setValue(totalVaccine);
                            }
                            else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    Button buttonSubVaccine = (Button) root.findViewById(R.id.subtractVaccine);
                    buttonSubVaccine.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int subVaccine = 0;
                            try {
                                subVaccine = Integer.parseInt(mEditVaccine.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currVaccine= Integer.parseInt(Displaycurrvaccine);
                            int value=currVaccine-subVaccine;
                            if(value>=0){
                                String totalVaccine=Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currVaccine").setValue(totalVaccine);
                            }else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    EditText mEditOxygen = (EditText) root.findViewById(R.id.editOxygen);
                    Button buttonAddOxygen = (Button) root.findViewById(R.id.addOxygen);
                    buttonAddOxygen.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int addOxygen = 0;
                            try {
                                addOxygen = Integer.parseInt(mEditOxygen.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currOxygen= Integer.parseInt(Displaycurroxygen);
                            int value=currOxygen+addOxygen;
                            if(value>0) {
                                String totalOxygen=Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currOxygen").setValue(totalOxygen);
                            }
                            else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    Button buttonSubOxygen = (Button) root.findViewById(R.id.subtractOxygen);
                    buttonSubOxygen.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int subOxygen = 0;
                            try {
                                subOxygen = Integer.parseInt(mEditOxygen.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currOxygen= Integer.parseInt(Displaycurroxygen);
                            int value=currOxygen-subOxygen;
                            if(value>0) {
                                String totalOxygen=Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currOxygen").setValue(totalOxygen);
                            }
                            else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    EditText mEditBeds = (EditText) root.findViewById(R.id.editICUBeds);
                    Button buttonAddICUBeds = (Button) root.findViewById(R.id.addICUBeds);
                    buttonAddICUBeds.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int addBeds = 0;
                            try {
                                addBeds = Integer.parseInt(mEditBeds.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currBeds= Integer.parseInt(DisplaycurrICUbeds);
                            int value=currBeds+addBeds;
                            if(value>0) {
                                String totalBeds=Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currICUBeds").setValue(totalBeds);
                            }
                            else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    Button buttonSubICUBeds = (Button) root.findViewById(R.id.subtractICUBeds);
                    buttonSubICUBeds.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int subBeds = 0;
                            try {
                                subBeds = Integer.parseInt(mEditBeds.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currBeds= Integer.parseInt(DisplaycurrICUbeds);
                            int value=currBeds-subBeds;
                            if(value>0) {
                                String totalBeds=Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currICUBeds").setValue(totalBeds);
                            }
                            else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    EditText mEditPlasma = (EditText) root.findViewById(R.id.editPlasma);
                    Button buttonAddPlasma = (Button) root.findViewById(R.id.addPlasma);
                    buttonAddPlasma.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int addPlasma = 0;
                            try {
                                addPlasma = Integer.parseInt(mEditPlasma.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currPlasma= Integer.parseInt(Displaycurrplasma);
                            int value=currPlasma+addPlasma;
                            if(value>0) {
                                String totalPlasma=Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currPlasma").setValue(totalPlasma);
                            }
                            else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                    Button buttonSubPlasma = (Button) root.findViewById(R.id.subtractPlasma);
                    buttonSubPlasma.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int subPlasma = 0;
                            try {
                                subPlasma = Integer.parseInt(mEditPlasma.getText().toString());
                            } catch (NumberFormatException nfe) {
                                nfe.printStackTrace();
                            }
                            int currPlasma= Integer.parseInt(Displaycurrplasma);
                            int value=currPlasma-subPlasma;
                            if(value>0) {
                                String totalPlasma=Integer.toString(value);
                                mDatabase.child("Institutes").child(user.getUid()).child("currPlasma").setValue(totalPlasma);
                            }
                            else{
                                Toast.makeText(getActivity(), "Minimum no of vaccines can be 0", Toast.LENGTH_SHORT).show();
                            }

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