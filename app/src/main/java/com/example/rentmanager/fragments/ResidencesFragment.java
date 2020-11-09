package com.example.rentmanager.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.rentmanager.R;
import com.example.rentmanager.Utils.InternetConnection;
import com.example.rentmanager.activities.AddAddressActivity;
import com.example.rentmanager.activities.AddResidenceActivity;
import com.example.rentmanager.databinding.FragmentResidencesBinding;
import com.example.rentmanager.http.ResidenceService;
import com.example.rentmanager.http.RetrofitClient;
import com.example.rentmanager.models.Residence;
import com.example.rentmanager.models.ResidenceList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResidencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResidencesFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Residence> residences;


    public ResidencesFragment() {
    }

    public static ResidencesFragment newInstance() {
        ResidencesFragment fragment = new ResidencesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadResidencesFromApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_residences, container, false);
        Button btnAddResidence = view.findViewById(R.id.btnAddResidence);
        btnAddResidence.setOnClickListener(v -> {
            Intent addAddressActivityIntent = new Intent(getContext(), AddResidenceActivity.class);
            startActivity(addAddressActivityIntent);
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    void loadResidencesFromApi() {
        if (InternetConnection.checkConnection(getContext())) {
            ResidenceService residenceService = RetrofitClient.getResidenceService();
            Call<ResidenceList> call = residenceService.getResidences();

            call.enqueue(new Callback<ResidenceList>() {
                @Override
                public void onResponse(Call<ResidenceList> call, Response<ResidenceList> response) {
                    if (response.isSuccessful()) {
                        residences = response.body().getResidences();
                    }
                }

                @Override
                public void onFailure(Call<ResidenceList> call, Throwable t) {
                    Toast.makeText(getContext(), "Error while fetching data from API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}