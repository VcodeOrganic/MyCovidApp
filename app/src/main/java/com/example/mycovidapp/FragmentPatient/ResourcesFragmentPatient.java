package com.example.mycovidapp.FragmentPatient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycovidapp.Maps.MapViewPatients;
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
 * Use the {@link ResourcesFragmentPatient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResourcesFragmentPatient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView ResourcesRecyclerList;
    private DatabaseReference mDatabaseRef;
    private ImageButton mSearchBtn;
    private Button mMarkInstitutes;
    private EditText mSearchField;

    public ResourcesFragmentPatient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResourcesFragmentPatient.
     */
    // TODO: Rename and change types and number of parameters
    public static ResourcesFragmentPatient newInstance(String param1, String param2) {
        ResourcesFragmentPatient fragment = new ResourcesFragmentPatient();
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

        View root = inflater.inflate(R.layout.fragment_resources_patient, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Institutes");

        ResourcesRecyclerList = (RecyclerView) root.findViewById(R.id.resourcesRecyclerList);
        ResourcesRecyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseUserSearch("");
        mSearchBtn = (ImageButton) root.findViewById(R.id.searchBtn);
        mSearchField = (EditText) root.findViewById(R.id.searchPincode);

        mMarkInstitutes = (Button) root.findViewById(R.id.btnMarkInstitutes);
        mMarkInstitutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapViewPatients.class);
                startActivity(intent);
            }
        });

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });

        return root;
    }

    private void firebaseUserSearch(String searchText) {
        super.onStart();

//        Toast.makeText(getActivity(), "Started Search", Toast.LENGTH_SHORT).show();

        Query firebaseSearchQuery = mDatabaseRef.orderByChild("instipincode").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Resources> options = new FirebaseRecyclerOptions.Builder<Resources>()
                .setQuery(firebaseSearchQuery, Resources.class)
                .build();

        FirebaseRecyclerAdapter<Resources, ResourcesViewHolder> adapter = new FirebaseRecyclerAdapter<Resources, ResourcesViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull final ResourcesViewHolder holder, final int position, @NonNull final Resources model) {
                holder.instiName.setText(model.getrInstiName());
                holder.instiPhone.setText(model.getrInstiPhone());
                holder.instiMail.setText(model.getrInstiMail());
                holder.instiAddress.setText(model.getrInstiAddress());
                holder.instiPincode.setText(model.getrInstipincode());
                holder.currVaccine.setText(model.getrCurrVaccine());
                holder.currOxygen.setText(model.getrCurrOxygen());
                holder.currICUBeds.setText(model.getrCurrICUBeds());
                holder.currPlasma.setText(model.getrCurrPlasma());

            }

            @NonNull
            @Override
            public ResourcesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.insti_card, viewGroup, false);
                ResourcesViewHolder viewHolder = new ResourcesViewHolder(view);
                return new ResourcesViewHolder(view);
            }


        };

        ResourcesRecyclerList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ResourcesViewHolder extends RecyclerView.ViewHolder {

        TextView instiName, instiPhone, instiMail, instiAddress, instiPincode, currVaccine, currOxygen, currICUBeds, currPlasma;

        public ResourcesViewHolder(@NonNull View itemView) {
            super(itemView);
            instiName = itemView.findViewById(R.id.instiNameRecycler);
            instiPhone = itemView.findViewById(R.id.instiPhoneRecycler);
            instiMail = itemView.findViewById(R.id.instiEmailRecycler);
            instiAddress = itemView.findViewById(R.id.instiAddressRecycler);
            instiPincode = itemView.findViewById(R.id.instiPincodeRecycler);
            currVaccine = itemView.findViewById(R.id.RecyclerVaccine);
            currOxygen = itemView.findViewById(R.id.RecyclerOxygen);
            currICUBeds = itemView.findViewById(R.id.RecyclerICUBeds);
            currPlasma = itemView.findViewById(R.id.RecyclerPlasma);

        }
    }


}