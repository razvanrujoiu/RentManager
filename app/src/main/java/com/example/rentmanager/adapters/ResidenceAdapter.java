package com.example.rentmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentmanager.R;
import com.example.rentmanager.databinding.ResidenceItemBinding;
import com.example.rentmanager.models.Residence;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class ResidenceAdapter extends RecyclerView.Adapter<ResidenceAdapter.ResidenceViewHolder> {

    private Context context;
    private ArrayList<Residence> residences;
    public static PublishSubject<Residence> removeResidencePublisher = PublishSubject.create();

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

    public class ResidenceViewHolder extends RecyclerView.ViewHolder {

        private ResidenceItemBinding binding;

        public ResidenceViewHolder(ResidenceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Residence residence) {
            binding.residenceDetails.setText(String.format("Number of rooms: %d, \nRental Price: %.2f \nSquare Feet: %.2f \nIs detached: %b \n" +
                            "Has Balcony: %b \nConstruction year: %.0f \nRental Price: %.2f \nEnd rental date: %s \n\nAddress: \n" +
                            "Street name: %s, %s \nPostal code: %s \nCity: %s \tCountry: %s",
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

            binding.deleteImageView.setOnClickListener(v -> {
                residences.remove(residence);
                notifyDataSetChanged();
                removeResidencePublisher.onNext(residence);
                Toast.makeText(context,"Residence has been removed",Toast.LENGTH_SHORT).show();
            });
        }
    }



    public PublishSubject<Residence> getRemoveResidencePublisher() {
        return this.removeResidencePublisher;
    }



}
