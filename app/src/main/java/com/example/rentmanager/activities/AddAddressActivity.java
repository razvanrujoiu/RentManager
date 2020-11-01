package com.example.rentmanager.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rentmanager.database.DatabaseClient;
import com.example.rentmanager.databinding.ActivityAddAddressBinding;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentmanager.R;
import com.example.rentmanager.models.Address;

public class AddAddressActivity extends AppCompatActivity {

    ActivityAddAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAddress();
            }
        });

    }

    private void saveAddress() {
        final String streetName = binding.streetName.getText().toString().trim();
        final String streetNumber = binding.streetNumber.getText().toString().trim();
        final String postalCode = binding.postalCode.getText().toString().trim();
        final String city = binding.city.getText().toString().trim();
        final String country = binding.country.toString().trim();

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

        class SaveAddress  extends AsyncTask<Void, Void, Void> {


            @Override
            protected Void doInBackground(Void... voids) {

                Address address = new Address(streetName,
                        streetNumber,
                        postalCode,
                        city,
                        country, 0L);

                DatabaseClient.getInstance(getApplicationContext())
                        .getRentManagerDatabase()
                        .addressDao()
                        .insert(address);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Toast.makeText(getApplicationContext(), "Address added", Toast.LENGTH_LONG).show();
            }
        }

        SaveAddress saveAddress = new SaveAddress();
        saveAddress.execute();
    }

}
