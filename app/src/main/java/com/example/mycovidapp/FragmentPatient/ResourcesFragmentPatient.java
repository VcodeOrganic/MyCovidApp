package com.example.mycovidapp.FragmentPatient;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mycovidapp.Models.Resources;
import com.example.mycovidapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
    private DatabaseReference ResourcesRef;

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
        ResourcesRef = FirebaseDatabase.getInstance().getReference().child("Institutes");

        ResourcesRecyclerList = (RecyclerView) root.findViewById(R.id.resourcesRecyclerList);
        ResourcesRecyclerList.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Resources> options = new FirebaseRecyclerOptions.Builder<Resources>()
                .setQuery(ResourcesRef, Resources.class)
                .build();

        FirebaseRecyclerAdapter<Resources, ResourcesViewHolder> adapter = new FirebaseRecyclerAdapter<Resources, ResourcesViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull final ResourcesViewHolder holder, final int position, @NonNull final Resources model) {
                holder.instiName.setText(model.getInstiName());
                holder.instiPhone.setText(model.getInstiPhone());
                holder.instiMail.setText(model.getInstiMail());
                holder.instiAddress.setText(model.getInstiAddress());
                holder.currVaccine.setText(model.getCurrVaccine());
                holder.currOxygen.setText(model.getCurrOxygen());
                holder.currICUBeds.setText(model.getCurrICUBeds());
                holder.currNICUBeds.setText(model.getCurrNBeds());
                holder.currPlasma.setText(model.getCurrPlasma());
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String match_id = getRef(position).getKey();
//
//                        Intent matchIntent = new Intent(getActivity(), TeamPreviewActivity.class);
//                        matchIntent.putExtra("match_id", match_id);
//                        matchIntent.putExtra("teamName1", model.getTeamName1());
//                        matchIntent.putExtra("teamName2", model.getTeamName2());
//                        startActivity(matchIntent);
//
//
//                    }
//                });

            }

            @NonNull
            @Override
            public ResourcesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.insti_card, viewGroup, false);
                return new ResourcesViewHolder(view);
            }


        };

        ResourcesRecyclerList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ResourcesViewHolder extends RecyclerView.ViewHolder {

        TextView instiName, instiPhone, instiMail, instiAddress, currVaccine, currOxygen, currICUBeds, currNICUBeds, currPlasma;

        public ResourcesViewHolder(@NonNull View itemView) {
            super(itemView);
            instiName = itemView.findViewById(R.id.instiNameRecycler);
            instiPhone = itemView.findViewById(R.id.instiPhoneRecycler);
            instiMail = itemView.findViewById(R.id.instiEmailRecycler);
            instiAddress = itemView.findViewById(R.id.instiAddressRecycler);
            currVaccine = itemView.findViewById(R.id.RecyclerVaccine);
            currOxygen = itemView.findViewById(R.id.RecyclerOxygen);
            currICUBeds = itemView.findViewById(R.id.RecyclerICUBeds);
            currNICUBeds = itemView.findViewById(R.id.RecyclerNICUBeds);
            currPlasma = itemView.findViewById(R.id.RecyclerPlasma);

        }
    }


}