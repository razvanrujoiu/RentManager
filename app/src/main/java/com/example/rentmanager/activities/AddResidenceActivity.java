package com.example.rentmanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.rentmanager.R;
import com.example.rentmanager.database.DatabaseClient;
import com.example.rentmanager.databinding.ActivityAddResidenceBinding;
import com.example.rentmanager.models.Residence;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddResidenceActivity extends AppCompatActivity {

    ActivityAddResidenceBinding binding;
    private int squareFeetSeekBarValue = 0;
    final Calendar calendar = Calendar.getInstance();
    long residenceId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddResidenceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addResidence.setOnClickListener(view -> saveResidence());

        setSeekBarSquareFeet();

        setDatePickerDialog();
    }

    private void setDatePickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDatePickerEditText();
        };
        binding.endRentalDate.setOnClickListener(v -> {
            new DatePickerDialog(AddResidenceActivity.this,
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void updateDatePickerEditText() {
        String format = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
        binding.endRentalDate.setText(dateFormat.format(calendar.getTime()));

    }


    private void setSeekBarSquareFeet() {
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
        boolean hasBalcony;
        if (binding.hasBalcony.isChecked()) {
            hasBalcony = true;
        } else {
            hasBalcony = false;
        }
        String constructionYear = binding.constructionYear.getText().toString().trim();
        String rentalPrice = binding.rentalPrice.getText().toString().trim();
        String endRentalDate = binding.endRentalDate.getText().toString().trim();

        if (numberOfRooms.isEmpty()) {
            binding.numberOfRooms.setError(getString(R.string.number_of_rooms_required_error));
            binding.numberOfRooms.requestFocus();
        }
        if (constructionYear.isEmpty()) {
            binding.constructionYear.setError(getString(R.string.construction_year_required_error));
            binding.constructionYear.requestFocus();
            return;
        }
        if(rentalPrice.isEmpty()) {
            binding.rentalPrice.setError(getString(R.string.rental_price_required_error));
            binding.rentalPrice.requestFocus();
            return;
        }
        if (endRentalDate.isEmpty()) {
            binding.endRentalDate.setError(getString(R.string.end_rental_date_required));
            binding.endRentalDate.requestFocus();
            return;
        }

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("user", MODE_PRIVATE);
        Long userIdForeignKey = sharedPreferences.getLong("userId", 0);


        class SaveResidence extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                Residence residence = new Residence(Integer.parseInt(numberOfRooms),
                        isDetached,
                        squareFeetSeekBarValue,
                        hasBalcony,
                        Double.parseDouble(constructionYear),
                        Double.parseDouble(rentalPrice),
                        endRentalDate,
                        userIdForeignKey);

                 residenceId = DatabaseClient.getInstance(getApplicationContext())
                        .getRentManagerDatabase()
                        .residenceDao()
                        .insert(residence);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Toast.makeText(getApplicationContext(), "Residence added", Toast.LENGTH_LONG).show();
                Intent addAddressIntent = new Intent(getApplicationContext(), AddAddressActivity.class);
                addAddressIntent.putExtra("residenceId", residenceId);
                startActivity(addAddressIntent);
            }
        }

        SaveResidence saveResidence = new SaveResidence();
        saveResidence.execute();
    }
}