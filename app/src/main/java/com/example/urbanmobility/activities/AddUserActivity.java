package com.example.urbanmobility.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.urbanmobility.databinding.ActivityAddResidenceBinding;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddResidenceBinding binding = ActivityAddResidenceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}