package com.example.sampleapp.atmlocator;

import android.app.ActionBar;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  sarath vaddi on 9/14/15.
 */
public class MapListActivity extends AppCompatActivity{

    android.support.v7.app.ActionBar.Tab MapTab, ListTab;
    Fragment MapfragmentTab = new MapViewFragment();
    Fragment ListFragmentTab = new ListFragment();
    public ArrayList<Atmdata> resultsList =  new ArrayList<Atmdata>();
    public String zipcode = null;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplistlayout);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            zipcode = extras.getString("zipcode");
        LatLng params = getLocationFromAddress(zipcode);
        String lat = String.valueOf(params.latitude);
        String lng = String.valueOf(params.longitude);
        ctx = this;
        AtmDataAsynctask task = new AtmDataAsynctask(ctx);
        task.execute(lat,lng);


    }


    public LatLng getLocationFromAddress(String zipcode)
    {
        LatLng p1=null;
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        try{
            address = coder.getFromLocationName(zipcode, 5);
            while(address.size()==0){
                address = coder.getFromLocationName(zipcode,5);
            }

            if(address.size()>0)
            {
                Address addr = address.get(0);
                p1= new LatLng(addr.getLatitude(),addr.getLongitude());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return p1;
    }

    public void setActionbar()
    {
        // Asking for the default ActionBar element that our platform supports.

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        Bundle bundle = new Bundle();
        bundle.putString("zipcode", zipcode );
        bundle.putParcelableArrayList("resultArray", resultsList);
        MapfragmentTab = new MapViewFragment();
        MapfragmentTab.setArguments(bundle);
        ListFragmentTab = new ListFragment();
        ListFragmentTab.setArguments(bundle);
        // Screen handling while hiding ActionBar icon.
        actionBar.setDisplayShowHomeEnabled(true);

        // Screen handling while hiding Actionbar title.
        actionBar.setDisplayShowTitleEnabled(true);

        // Creating ActionBar tabs.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        MapTab = actionBar.newTab().setText("MapView");
        ListTab = actionBar.newTab().setText("ListView");
        MapTab.setTabListener(new TabListener(MapfragmentTab));
        ListTab.setTabListener(new TabListener(ListFragmentTab));

        actionBar.addTab(MapTab);
        actionBar.addTab(ListTab);
    }
}
