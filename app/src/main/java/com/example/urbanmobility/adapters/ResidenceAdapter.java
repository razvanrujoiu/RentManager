package com.example.urbanmobility.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urbanmobility.databinding.ResidenceItemBinding;
import com.example.urbanmobility.models.Route;

import java.util.ArrayList;

import io.reactivex.subjects.PublishSubject;

public class ResidenceAdapter extends RecyclerView.Adapter<ResidenceAdapter.ResidenceViewHolder> {

    private Context context;
    private ArrayList<Route> routes;
    public static PublishSubject<Route> removeResidencePublisher = PublishSubject.create();

    public ResidenceAdapter(Context context, ArrayList<Route> routes) {
        this.context = context;
        this.routes = routes;
    }

    @NonNull
    @Override
    public ResidenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ResidenceItemBinding binding = ResidenceItemBinding.inflate(layoutInflater, parent, false);
        return new ResidenceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResidenceViewHolder holder, int position) {
        Route route = routes.get(position);
        holder.bind(route);
    }

    @Override
    public int getItemCount() {
        return routes != null ? routes.size() : 0;
    }

    public class ResidenceViewHolder extends RecyclerView.ViewHolder {

        private ResidenceItemBinding binding;

        public ResidenceViewHolder(ResidenceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Route route) {
            binding.residenceDetails.setText(String.format("Number of rooms: %d, \nRental Price: %.2f \nSquare Feet: %.2f \nIs detached: %b \n" +
                            "Has Balcony: %b \nConstruction year: %.0f \nRental Price: %.2f \nEnd rental date: %s \n\nAddress: \n" +
                            "Street name: %s, %s \nPostal code: %s \nCity: %s \tCountry: %s",
                    route.getNumberOfRooms(),
                    route.getRentalPrice(),
                    route.getSquareFeet(),
                    route.isDetached(),
                    route.isHasBalcony(),
                    route.getConstructionYear(),
                    route.getRentalPrice(),
                    route.getEstimatedTime(),
                    route.getStation().getStreetName(),
                    route.getStation().getStationName(),
                    route.getStation().getArrivalHour(),
                    route.getStation().getDepartureHour(),
                    route.getStation().getCountry()
                    ));

            binding.deleteImageView.setOnClickListener(v -> {
                routes.remove(route);
                notifyDataSetChanged();
                removeResidencePublisher.onNext(route);
                Toast.makeText(context,"Residence has been removed",Toast.LENGTH_SHORT).show();
            });
        }
    }



    public PublishSubject<Route> getRemoveResidencePublisher() {
        return this.removeResidencePublisher;
    }



}
