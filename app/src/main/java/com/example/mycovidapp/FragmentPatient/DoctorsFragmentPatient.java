package com.example.mycovidapp.FragmentPatient;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycovidapp.Models.Doctors;
import com.example.mycovidapp.Models.Resources;
import com.example.mycovidapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoctorsFragmentPatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoctorsFragmentPatient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView DoctorRecyclerList;
    private DatabaseReference mDatabaseRef;
    private static final Integer REQUEST_CODE = 1;
    Button callBtn;
    public DoctorsFragmentPatient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoctorsFragmentPatient.
     */
    // TODO: Rename and change types and number of parameters
    public static DoctorsFragmentPatient newInstance(String param1, String param2) {
        DoctorsFragmentPatient fragment = new DoctorsFragmentPatient();
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
        View root = inflater.inflate(R.layout.fragment_doctors_patient, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Doctors");

        DoctorRecyclerList = (RecyclerView) root.findViewById(R.id.doctorRecyclerList);
        DoctorRecyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }


    //Performing action on button click

    public void onStart(){
        super.onStart();

        FirebaseRecyclerOptions<Doctors> options = new FirebaseRecyclerOptions.Builder<Doctors>()
                .setQuery(mDatabaseRef, Doctors.class)
                .build();

        FirebaseRecyclerAdapter<Doctors, DoctorsFragmentPatient.DoctorViewHolder> adapter = new FirebaseRecyclerAdapter<Doctors, DoctorsFragmentPatient.DoctorViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull final DoctorsFragmentPatient.DoctorViewHolder holder, final int position, @NonNull final Doctors model) {
                holder.docName.setText(model.getDocName());
                holder.docInsti.setText(model.getDocInsti());
                holder.docPhone.setText(model.getDocPhone());
                holder.docTime.setText(model.getDocCallingHours());

            }

            @NonNull
            @Override
            public DoctorsFragmentPatient.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctor_card, viewGroup, false);
                DoctorsFragmentPatient.DoctorViewHolder viewHolder = new DoctorsFragmentPatient.DoctorViewHolder(view);
                return new DoctorsFragmentPatient.DoctorViewHolder(view);
            }


        };

        DoctorRecyclerList.setAdapter(adapter);
        adapter.startListening();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {

        TextView docName, docInsti, docPhone, docTime;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            docName = itemView.findViewById(R.id.docNameRecycler);
            docInsti = itemView.findViewById(R.id.docInstiRecycler);
            docPhone = itemView.findViewById(R.id.docPhoneRecycler);
            docTime = itemView.findViewById(R.id.docTimeRecycler);

            itemView.findViewById(R.id.callBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        String number=docPhone.getText().toString();
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+number));
                    if (ActivityCompat.checkSelfPermission( getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    }
                    else {
                        ActivityCompat.requestPermissions(
                                getActivity(),
                                new String[]{Manifest.permission.CALL_PHONE},
                                REQUEST_CODE);
                    }

                }
            });

            itemView.findViewById(R.id.callText).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String number=docPhone.getText().toString();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+number));
                    if (ActivityCompat.checkSelfPermission( getActivity(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    }
                    else {
                        ActivityCompat.requestPermissions(
                                getActivity(),
                                new String[]{Manifest.permission.CALL_PHONE},
                                REQUEST_CODE);
                    }
                }
            });
        }
    }
}