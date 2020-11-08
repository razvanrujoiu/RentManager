package com.example.rentmanager.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.rentmanager.R;
import com.example.rentmanager.activities.AddAddressActivity;
import com.example.rentmanager.activities.AddResidenceActivity;
import com.example.rentmanager.databinding.FragmentResidencesBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResidencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResidencesFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ResidencesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ResidencesFragment newInstance() {
        ResidencesFragment fragment = new ResidencesFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_residences, container, false);
        Button btnAddResidence = view.findViewById(R.id.btnAddResidence);
        btnAddResidence.setOnClickListener(v -> {
            Intent addAddressActivityIntent = new Intent(getContext(), AddResidenceActivity.class);
            startActivity(addAddressActivityIntent);
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}