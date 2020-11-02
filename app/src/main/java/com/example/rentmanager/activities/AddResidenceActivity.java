package com.example.rentmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

import com.example.rentmanager.databinding.ActivityAddResidenceBinding;

public class AddResidenceActivity extends AppCompatActivity {

    ActivityAddResidenceBinding binding;
    private int squareFeetSeekBarValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddResidenceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addResidence.setOnClickListener(view -> saveResidence());

        binding.seekBarSquareFeet.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                squareFeetSeekBarValue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                binding.squareFeetTextView.setText(squareFeetSeekBarValue + "/" + seekBar.getMax());
            }
        });
    }

    private void saveResidence() {
        final String numberOfRooms = binding.numberOfRooms.getText().toString().trim();
        boolean isDetached;
        if (binding.isDetached.isChecked()) {
            isDetached = true;
        } else {
            isDetached = false;
        }

    }

}