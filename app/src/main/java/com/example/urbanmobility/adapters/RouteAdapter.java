package com.example.urbanmobility.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.urbanmobility.databinding.RouteItemBinding;
import com.example.urbanmobility.databinding.StationItemBinding;
import com.example.urbanmobility.models.MockRoute;
import com.example.urbanmobility.models.MockStation;

import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Route;


public class RouteAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<MockRoute> routes;

    public RouteAdapter(Context context, ArrayList<MockRoute> routes) {
        this.context = context;
        this.routes = routes;
    }

    @Override
    public int getGroupCount() {
        return routes.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return routes.get(i).getStations().size();
    }

    @Override
    public Object getGroup(int i) {
        return routes.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return routes.get(i).getStations().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        MockRoute route = routes.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);
        RouteItemBinding routeItemBinding = RouteItemBinding.inflate(inflater,viewGroup,false);
        routeItemBinding.routeNoTV.setText(Integer.toString(route.getRouteNo()));
        routeItemBinding.estimatedTimeTV.setText(Integer.toString(route.getEstimatedRouteTime()));
        routeItemBinding.stationsNumberTV.setText(Integer.toString(route.getStationsNo()));

        return routeItemBinding.getRoot();
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        MockStation station = routes.get(i).getStations().get(i1);
        LayoutInflater inflater = LayoutInflater.from(context);
        StationItemBinding stationItemBinding = StationItemBinding.inflate(inflater,viewGroup,false);
        stationItemBinding.streetNameTV.setText(station.getStreetName());
        stationItemBinding.stationNameTV.setText(station.getName());
        stationItemBinding.arrivalHourTV.setText(station.getArrivalHour());
        stationItemBinding.departureHourTV.setText(station.getDepartureHour());
        return stationItemBinding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
