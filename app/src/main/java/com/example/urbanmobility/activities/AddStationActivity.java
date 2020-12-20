package com.example.urbanmobility.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanmobility.R;
import com.example.urbanmobility.adapters.StationAdapter;
import com.example.urbanmobility.databinding.ActivityAddStationsBinding;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.Station;
import com.example.urbanmobility.viewmodels.RouteViewModel;

import java.util.ArrayList;

public class AddStationActivity extends AppCompatActivity {

    ActivityAddStationsBinding binding;
    Route routeFromIntent;
    RouteViewModel routeViewModel;
    ArrayList<Station> stations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        StationAdapter stationAdapter = new StationAdapter(this,stations);
        binding.recyclerView.setAdapter(stationAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        binding.addStation.setOnClickListener(view -> addStation());
        binding.addStations.setOnClickListener(view -> saveWholeRoute());

        routeFromIntent = (Route) getIntent().getSerializableExtra("route");
        routeViewModel = new RouteViewModel(getApplication());
    }

    private void addStation() {
        final String streetName = binding.streetName.getText().toString().trim();
        final String stationName = binding.stationName.getText().toString().trim();
        final String departureTime = binding.departureTime.getText().toString().trim();
        final String arrivalTime = binding.arrivalTime.getText().toString().trim();


        if (streetName.isEmpty()) {
            binding.streetName.setError(getString(R.string.street_name_required_error));
            binding.streetName.requestFocus();
            return;
        }

        if (stationName.isEmpty()) {
            binding.stationName.setError(getString(R.string.station_name_required));
            binding.stationName.requestFocus();
            return;
        }
        if (departureTime.isEmpty()) {
            binding.departureTime.setError(getString(R.string.time));
            binding.departureTime.requestFocus();
            return;
        }
        if (arrivalTime.isEmpty()) {
            binding.arrivalTime.setError(getString(R.string.time));
            binding.arrivalTime.requestFocus();
            return;
        }
        Station station = new Station(streetName,stationName,arrivalTime,departureTime);
        stations.add(station);
        binding.recyclerView.getAdapter().notifyDataSetChanged();
    }


    public void saveWholeRoute() {
        class SaveResidence extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                routeFromIntent.setStationList(stations);
                routeViewModel.insertRoute(routeFromIntent);
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
