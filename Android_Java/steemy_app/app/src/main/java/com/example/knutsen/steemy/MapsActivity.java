package com.example.knutsen.steemy;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    double latd;
    double log;
    double devlat, devlong;


    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent myIntent = getIntent();
        latd = myIntent.getDoubleExtra("LATITUDE", 0);
        log = myIntent.getDoubleExtra("LONGTITUDE", 0);
        devlat = myIntent.getDoubleExtra("DEVLAT",0);
        devlong = myIntent.getDoubleExtra("DEVLONG",0);


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        //double latitude = getIntent().getDoubleExtra("LATITUDE", 34);
        //double longtitude = getIntent().getDoubleExtra("LONGTITUDE", 151);
        //LatLng posit = new LatLng(latitude, longtitude);
        mMap = googleMap;





        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latd,log);
        LatLng devLocation = new LatLng(devlat,devlong);
        mMap.addMarker(new MarkerOptions().position(devLocation));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10), 5000,null);
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



}
