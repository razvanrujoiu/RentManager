package com.example.urbanmobility.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanmobility.databinding.StationItemBinding;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.Station;

import java.util.ArrayList;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {

    private Context context;
    private ArrayList<Station> stations;

    public StationAdapter(Context context, ArrayList<Station> stations) {
        this.context = context;
        this.stations = stations;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        StationItemBinding binding = StationItemBinding.inflate(layoutInflater, parent, false);
        return new StationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stations.get(position);
        holder.bind(station);
    }

    @Override
    public int getItemCount() {
        return stations != null ? stations.size() : 0;
    }

    public class StationViewHolder extends RecyclerView.ViewHolder {

        private StationItemBinding binding;

        public StationViewHolder(StationItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Station station) {
            binding.arrivalHourTV.setText(station.getArrivalHour());
            binding.departureHourTV.setText(station.getDepartureHour());
            binding.stationNameTV.setText(station.getStationName());
            binding.streetNameTV.setText(station.getStreetName());
        }
    }


}
