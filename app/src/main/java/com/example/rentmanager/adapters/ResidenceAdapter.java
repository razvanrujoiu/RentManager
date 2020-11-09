package com.example.rentmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentmanager.databinding.ResidenceItemBinding;
import com.example.rentmanager.models.Residence;

import java.util.ArrayList;

public class ResidenceAdapter extends RecyclerView.Adapter<ResidenceAdapter.ResidenceViewHolder> {

    private Context context;
    private ArrayList<Residence> residences;

    public ResidenceAdapter(Context context, ArrayList<Residence> residences) {
        this.context = context;
        this.residences = residences;
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
        Residence residence = residences.get(position);
        holder.bind(residence);
    }

    @Override
    public int getItemCount() {
        return residences != null ? residences.size() : 0;
    }

    public class ResidenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ResidenceItemBinding binding;


        public ResidenceViewHolder(ResidenceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void bind(Residence residence) {
            binding.residenceDetails.setText(String.format("Number of rooms: %d, \t Rental Price: %.2f \n Square Feet: %.2f \t Is detached: %b \n" +
                            "Has Balcony: %b \t Construction year: %.0f \n Rental Price: %.2f \t End rental date: %s \n Address: \n" +
                            "Street name: %s, %s \t Postal code: %s \n City: %s \t Country: %s",
                    residence.getNumberOfRooms(),
                    residence.getRentalPrice(),
                    residence.getSquareFeet(),
                    residence.isDetached(),
                    residence.isHasBalcony(),
                    residence.getConstructionYear(),
                    residence.getRentalPrice(),
                    residence.getEndRentalDate(),
                    residence.getAddress().getStreetName(),
                    residence.getAddress().getNumber(),
                    residence.getAddress().getPostalCode(),
                    residence.getAddress().getCity(),
                    residence.getAddress().getCountry()
                    ));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
