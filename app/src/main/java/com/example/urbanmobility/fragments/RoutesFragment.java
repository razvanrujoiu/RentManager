package com.example.urbanmobility.fragments;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.urbanmobility.R;
import com.example.urbanmobility.Utils.InternetConnection;
import com.example.urbanmobility.adapters.RouteAdapter;
import com.example.urbanmobility.databinding.FragmentRoutesBinding;
import com.example.urbanmobility.http.RetrofitClient;
import com.example.urbanmobility.http.RouteService;
import com.example.urbanmobility.models.Route;
import com.example.urbanmobility.models.ResidenceList;
import com.example.urbanmobility.models.RouteWithStations;
import com.example.urbanmobility.models.Station;
import com.example.urbanmobility.viewmodels.RouteViewModel;

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
    private ArrayList<RouteWithStations> routes = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RouteViewModel routeViewModel;
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
        routeViewModel = new RouteViewModel(application);

        routeViewModel.getAllRoutes().observe(this, residences -> {
            this.routes.clear();
            this.routes.addAll(residences);
            adapter.notifyDataSetChanged();
        });

        Station mockStation = new Station("Bd Expozitiei","Orlando","21:00","14:00");
        Station mockStation1 = new Station("Calea Victoriei","Banu Manta","22:00","14:00");
        Station mockStation2 = new Station("Calea Grivitei","Aviator Popisteanu","12:00","14:00");
        Station mockStation3 = new Station("Ion Mihalache","Chibrit","13:00","14:00");
        ArrayList<Station> stations = new ArrayList<>();
        stations.add(mockStation);
        stations.add(mockStation1);
        stations.add(mockStation2);
        stations.add(mockStation3);
        Route mockRoute = new Route("1h45m", "335N", 0L);
        Route mockRoute1 = new Route("1h22m", "56", 0L);
        Route mockRoute2 = new Route("45m", "120", 0L);
        ArrayList<Route> routes = new ArrayList<>();
        this.routes.add(new RouteWithStations(mockRoute,stations));
        this.routes.add(new RouteWithStations(mockRoute1,stations));
        this.routes.add(new RouteWithStations(mockRoute2,stations));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentRoutesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        expandableListView = binding.expandableRoutesList;

        layoutManager = new LinearLayoutManager(getContext());

        setExpandableListViewAdapter();

        binding.btnGetResidencesFromApi.setOnClickListener(v -> {
            if (routes != null) {
                routes.clear();
                adapter.notifyDataSetChanged();
            }
            binding.progressBar.setVisibility(View.VISIBLE);
            loadResidencesFromApi();
        });

        binding.btnDeleteAllResidences.setOnClickListener(v ->{
            if(routes != null) {
                routes.clear();
                adapter.notifyDataSetChanged();
                routeViewModel.deleteAllRoutes();
            }
        });

//        compositeDisposable.add(RouteAdapter.removeResidencePublisher.subscribe(residence ->
//                residenceViewModel.deleteRoute(residence)
//        ));

        return view;
    }


    private void setExpandableListViewAdapter() {

        adapter = new RouteAdapter(getContext(), routes);
        expandableListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }

    void loadResidencesFromApi() {
        if (InternetConnection.checkConnection(getContext())) {
            RouteService routeService = RetrofitClient.getResidenceService();
            Call<ResidenceList> call = routeService.getResidences();

            call.enqueue(new Callback<ResidenceList>() {
                @Override
                public void onResponse(Call<ResidenceList> call, Response<ResidenceList> response) {
                    if (response.isSuccessful()) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                        long userId = sharedPreferences.getLong("userId", 0);
                        for (RouteWithStations route : response.body().getRoutes()) {
                            route.route.setUserIdForeignKey(userId);
                            routeViewModel.insertRoute(route);
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