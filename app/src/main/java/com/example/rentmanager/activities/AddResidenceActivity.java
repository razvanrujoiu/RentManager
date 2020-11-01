package com.example.rentmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rentmanager.databinding.ActivityAddResidenceBinding;

public class AddResidenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddResidenceBinding binding = ActivityAddResidenceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


}