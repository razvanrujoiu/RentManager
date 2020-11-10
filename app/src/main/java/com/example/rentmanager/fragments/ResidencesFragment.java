package com.example.rentmanager.fragments;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.rentmanager.R;
import com.example.rentmanager.Utils.InternetConnection;
import com.example.rentmanager.activities.AddAddressActivity;
import com.example.rentmanager.activities.AddResidenceActivity;
import com.example.rentmanager.adapters.ResidenceAdapter;
import com.example.rentmanager.databinding.FragmentResidencesBinding;
import com.example.rentmanager.http.ResidenceService;
import com.example.rentmanager.http.RetrofitClient;
import com.example.rentmanager.models.Residence;
import com.example.rentmanager.models.ResidenceList;
import com.example.rentmanager.viewmodels.ResidenceViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResidencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResidencesFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ResidenceAdapter adapter;
    private FragmentResidencesBinding binding;
    private static Application application;
    private ArrayList<Residence> residences = new ArrayList<>();
    LinearLayoutManager layoutManager;
    private ResidenceViewModel residenceViewModel;

    public ResidencesFragment() {
    }

    public static ResidencesFragment newInstance(Application app) {
        ResidencesFragment fragment = new ResidencesFragment();
        application = app;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        residenceViewModel = new ResidenceViewModel(application);

        residenceViewModel.getAllResidences().observe(this, residences -> {
            this.residences.addAll(residences);
            setRecyclerViewAdapter();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentResidencesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        recyclerView = binding.residencesRecyclerView;

        layoutManager = new LinearLayoutManager(getContext());

        setRecyclerViewAdapter();

        binding.btnGetResidencesFromApi.setOnClickListener(v -> {
            if (residences != null) {
                residences.clear();
                adapter.notifyDataSetChanged();
            }
            binding.progressBar.setVisibility(View.VISIBLE);
            loadResidencesFromApi();
        });

        return view;
    }

    private void setRecyclerViewAdapter() {
        adapter = new ResidenceAdapter(getContext(), residences);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
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
                        binding.progressBar.setVisibility(View.INVISIBLE);
                        residences = response.body().getResidences();
                        setRecyclerViewAdapter();
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