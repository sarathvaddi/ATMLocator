package com.example.sampleapp.atmlocator;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  sarath vaddi on 9/14/15.
 */

public class AtmDataAsynctask extends AsyncTask<String,Integer,ArrayList<Atmdata>>{

     String CHASEATM_URL = "https://m.chase.com/PSRWeb/location/list.action?lat={0}&lng={1}";
     Context ctx;
    MapListActivity activityt;

    public AtmDataAsynctask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected ArrayList<Atmdata> doInBackground(String... params) {
        ArrayList<Atmdata> results = new ArrayList<Atmdata>();
        CHASEATM_URL = "https://m.chase.com/PSRWeb/location/list.action?lat="+params[0]+"&lng="+params[1];
        // Making HTTP request
        try {
            // defaultHttpClient
            HttpGet httpPost = new HttpGet(CHASEATM_URL);
            HttpParams httpParameters = new BasicHttpParams();

            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            httpClient.setParams(httpParameters);
            HttpResponse response = httpClient.execute(httpPost);
            Log.d("Status", response.toString());
            int responseCode = response.getStatusLine().getStatusCode();
            if(responseCode == 200) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);
                    Log.d("Responce", responseBody.toString());
                    String jsonString = responseBody.toString();
                    JSONObject obj = new JSONObject(jsonString);
                    JSONArray locationArray = obj.getJSONArray("locations");
                    for(int x = 0; x < locationArray.length(); x++)
                    {
                        Atmdata item = new Atmdata();
                        item.setState(locationArray.getJSONObject(x).getString("state"));
                        item.setLocType(locationArray.getJSONObject(x).getString("locType"));
                        item.setLabel(locationArray.getJSONObject(x).getString("label"));
                        item.setAddress(locationArray.getJSONObject(x).getString("address"));
                        item.setCity(locationArray.getJSONObject(x).getString("city"));
                        item.setZip(locationArray.getJSONObject(x).getString("zip"));
                        item.setName(locationArray.getJSONObject(x).getString("name"));
                        item.setLat(locationArray.getJSONObject(x).getString("lat"));
                        item.setLng(locationArray.getJSONObject(x).getString("lng"));
                        item.setBank(locationArray.getJSONObject(x).getString("bank"));
                        item.setPhone(locationArray.getJSONObject(x).getString("phone"));
                        item.setDistance(locationArray.getJSONObject(x).getString("distance"));
                        results.add(item);

                    }
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return results;
    }

        @Override
    protected void onPostExecute(ArrayList<Atmdata> atmdatalist) {
        super.onPostExecute(atmdatalist);
            if(atmdatalist!=null) {
                activityt = (MapListActivity)ctx;
                activityt.resultsList = atmdatalist;
                activityt.setActionbar();
            }

    }
}
