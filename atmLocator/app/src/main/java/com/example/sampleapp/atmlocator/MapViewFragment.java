package com.example.sampleapp.atmlocator;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by sarath vaddi on 9/14/15.
 */
public class MapViewFragment extends Fragment{

    MapView mapView;
    GoogleMap map;
    public LocationManager locationManager;
    String zipcode;
    ArrayList<Atmdata> mapdata = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mapfragment_layout, container, false);
        zipcode = this.getArguments().getString("zipcode");
        mapdata = this.getArguments().getParcelableArrayList("resultArray");
        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.setMyLocationEnabled(true);
        locationManager = ((LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE));
        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
         MapsInitializer.initialize(this.getActivity());
        LatLngBounds.Builder b = new LatLngBounds.Builder();
        if(mapdata!=null && mapdata.size()>0) {
            for(int i=0;i<mapdata.size();i++) {
                LatLng CENTER = null;
                Atmdata item = mapdata.get(i);
                Double lat = Double.parseDouble(item.getLat());
                Double lng = Double.parseDouble(item.getLng());
                CENTER = new LatLng(lat,lng);
                try {
                    MarkerOptions m = new MarkerOptions().position(CENTER).title(item.getName()).snippet(item.getAddress()).alpha(0.7f);
                    map.addMarker(m);
                    b.include(CENTER);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //Change the padding as per needed
        LatLngBounds bounds = b.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 50,50,5);
        map.animateCamera(cu);
        map.setIndoorEnabled(true);
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}


