package com.example.urbanmobility.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.urbanmobility.R;
import com.example.urbanmobility.databinding.ActivityAddRouteBinding;
import com.example.urbanmobility.models.Route;

import java.util.Calendar;

public class AddRouteActivity extends AppCompatActivity {

    ActivityAddRouteBinding binding;
    private int squareFeetSeekBarValue = 0;
    final Calendar calendar = Calendar.getInstance();
    int LAUNCH_ADD_ADDRESS_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRouteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addRoute.setOnClickListener(view -> saveResidence());

        setSeekBarSquareFeet();
    }




    private void setSeekBarSquareFeet() {
        binding.seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                squareFeetSeekBarValue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                binding.estimatedTimeTextView.setText(squareFeetSeekBarValue + " min");
            }
        });
    }


    private void saveResidence() {
        String routeNumber = binding.routeNoTextView.getText().toString().trim();
        String estimatedTime = binding.estimatedTimeTextView.getText().toString().trim();

        if (routeNumber.isEmpty()) {
            binding.routeNoTextView.setError(getString(R.string.route_number_required));
            binding.routeNoTextView.requestFocus();
            return;
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        Long userIdForeignKey = sharedPreferences.getLong("userId", 0);

        Route route = new Route(estimatedTime,routeNumber,userIdForeignKey);
        Intent addAddressIntent = new Intent(getApplicationContext(), AddStationActivity.class);
        addAddressIntent.putExtra("route", route);
        startActivityForResult(addAddressIntent, LAUNCH_ADD_ADDRESS_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_ADDRESS_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Route added", Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }
}