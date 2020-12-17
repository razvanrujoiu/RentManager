package com.example.urbanmobility.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.urbanmobility.databinding.ActivityAddAddressBinding;

import androidx.appcompat.app.AppCompatActivity;

import com.example.urbanmobility.R;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.Station;
import com.example.urbanmobility.viewmodels.ResidenceViewModel;

public class AddStationActivity extends AppCompatActivity {

    ActivityAddAddressBinding binding;
    Route routeFromIntent;
    ResidenceViewModel residenceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addAddressBtn.setOnClickListener(view -> saveAddress());

        routeFromIntent = (Route) getIntent().getSerializableExtra("residence");
        residenceViewModel = new ResidenceViewModel(getApplication());
    }

    private void saveAddress() {
        final String streetName = binding.streetName.getText().toString().trim();
        final String streetNumber = binding.streetNumber.getText().toString().trim();
        final String postalCode = binding.postalCode.getText().toString().trim();
        final String city = binding.city.getText().toString().trim();
        final String country = binding.country.getText().toString().trim();

        if (streetName.isEmpty()) {
            binding.streetName.setError(getString(R.string.street_name_required_error));
            binding.streetName.requestFocus();
            return;
        }

        if (streetNumber.isEmpty()) {
            binding.streetNumber.setError(getString(R.string.street_number_required_error));
            binding.streetNumber.requestFocus();
            return;
        }

        if (postalCode.isEmpty()) {
            binding.postalCode.setError(getString(R.string.postal_code_required_error));
            binding.postalCode.requestFocus();
            return;
        }

        if (city.isEmpty()) {
            binding.city.setError(getString(R.string.city_required_error));
            binding.city.requestFocus();
            return;
        }

        if(country.isEmpty()) {
            binding.country.setError(getString(R.string.country_required_error));
            binding.country.requestFocus();
            return;
        }

        class SaveResidence extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

//                Station station = new Station(streetName,
//                        streetNumber,
//                        postalCode,
//                        city);

//                routeFromIntent.setStation(station);

//                residenceViewModel.insertResidence(routeFromIntent);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        }

        SaveResidence saveAddress = new SaveResidence();
        saveAddress.execute();
    }

}
