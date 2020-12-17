package com.example.urbanmobility.fragments;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.urbanmobility.Utils.InternetConnection;
import com.example.urbanmobility.adapters.RouteAdapter;
import com.example.urbanmobility.databinding.FragmentRoutesBinding;
import com.example.urbanmobility.http.ResidenceService;
import com.example.urbanmobility.http.RetrofitClient;
import com.example.urbanmobility.models.MockRoute;
import com.example.urbanmobility.models.MockStation;
import com.example.urbanmobility.models.Residence;
import com.example.urbanmobility.models.ResidenceList;
import com.example.urbanmobility.viewmodels.ResidenceViewModel;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoutesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoutesFragment extends Fragment implements View.OnClickListener {

    private ExpandableListView expandableListView;
    private RouteAdapter adapter;
    private FragmentRoutesBinding binding;
    private static Application application;
    private ArrayList<Residence> residences = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private ResidenceViewModel residenceViewModel;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public RoutesFragment() {
    }

    public static RoutesFragment newInstance(Application app) {
        RoutesFragment fragment = new RoutesFragment();
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
            this.residences.clear();
            this.residences.addAll(residences);
            setRecyclerViewAdapter();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRoutesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        expandableListView = binding.expandableRoutesList;

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

        binding.btnDeleteAllResidences.setOnClickListener(v ->{
            if(residences != null) {
                residences.clear();
                adapter.notifyDataSetChanged();
                residenceViewModel.deleteAllResidences();
            }
        });

//        compositeDisposable.add(RouteAdapter.removeResidencePublisher.subscribe(residence ->
//                residenceViewModel.deleteResidence(residence)
//        ));

        return view;
    }


    private void setRecyclerViewAdapter() {
        MockStation mockStation = new MockStation("dasda","asdasd","dasda","asdasd");
        MockStation mockStation1 = new MockStation("dasda","asdasd","dasda","asdasd");
        MockStation mockStation2 = new MockStation("dasda","asdasd","dasda","asdasd");
        MockStation mockStation3 = new MockStation("dasda","asdasd","dasda","asdasd");
        ArrayList<MockStation> stations = new ArrayList<>();
        stations.add(mockStation);
        stations.add(mockStation1);
        stations.add(mockStation2);
        stations.add(mockStation3);
        MockRoute mockRoute = new MockRoute(4,34,31,stations);
        MockRoute mockRoute1 = new MockRoute(4,34,31,stations);
        MockRoute mockRoute2 = new MockRoute(4,34,31,stations);
        ArrayList<MockRoute> routes = new ArrayList<>();
        routes.add(mockRoute);
        routes.add(mockRoute1);
        routes.add(mockRoute2);
        adapter = new RouteAdapter(getContext(),routes);
        expandableListView.setAdapter(adapter);
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
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                        long userId = sharedPreferences.getLong("userId", 0);
                        for (Residence residence: response.body().getResidences()) {
                            residence.setUserIdForeignKey(userId);
                            residenceViewModel.insertResidence(residence);
                        }
                        binding.progressBar.setVisibility(View.INVISIBLE);
//                        setRecyclerViewAdapter();
                    }
                }

                @Override
                public void onFailure(Call<ResidenceList> call, Throwable t) {
                    Toast.makeText(getContext(), "Error while fetching data from API", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}