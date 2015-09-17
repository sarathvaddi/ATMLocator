package com.example.sampleapp.atmlocator;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sarath vaddi on 9/14/15.
 */
public class ListFragment extends Fragment {
    ArrayList<Atmdata> mapdata = null;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listviewfragment_layout, container, false);

        mapdata = this.getArguments().getParcelableArrayList("resultArray");
        final ListView listview = (ListView) rootView.findViewById(R.id.listview);

        final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(),R.layout.listrowlayout, mapdata);
        listview.setAdapter(adapter);
        return rootView;
    }

    private class StableArrayAdapter extends ArrayAdapter<Atmdata> {

        HashMap<Atmdata, Integer> mIdMap = new HashMap<Atmdata, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  ArrayList<Atmdata> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            Atmdata item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.listrowlayout, null);
            }

            Atmdata item = getItem(position);
            if(item!=null)
            {
                TextView Address = (TextView)v.findViewById(R.id.Address);
                TextView city  = (TextView)v.findViewById(R.id.city);
                TextView zip   = (TextView)v.findViewById(R.id.zip);
                TextView state = (TextView)v.findViewById(R.id.state);

                if(Address!=null)
                    Address.setText(item.getAddress());
                if(city !=null)
                    city.setText(item.getCity());

                if(zip !=null)
                    zip.setText(item.getZip());
                if(state!=null)
                    state.setText(item.getState());
            }
            return v;
        }
    }

}
