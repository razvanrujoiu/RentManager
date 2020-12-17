package com.example.urbanmobility.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urbanmobility.activities.AddRouteActivity;
import com.example.urbanmobility.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnAddRoute.setOnClickListener(v -> {
            Intent addAddressActivityIntent = new Intent(getContext(), AddRouteActivity.class);
            startActivity(addAddressActivityIntent);
        });
        return view;
    }
}