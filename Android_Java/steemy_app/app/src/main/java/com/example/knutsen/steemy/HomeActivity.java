package com.example.knutsen.steemy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.json.JSONObject;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;




public class HomeActivity extends AppCompatActivity implements LocationListener{

    private static final int    MAXPOSITIONS = 20;
    private String[] positions = new String[MAXPOSITIONS];
    private ArrayAdapter<String> myAdapter;



    //String username = "kristoferknutsen@hotmail.com";
    //String password = "slednecks1";
    String username = "zersena@yahoo.com";
    String password = "Newselam12";

    Vibrator mapVibrator;

    int hum;
    int lum;
    int temp;
    int pres;
    int bat;
    double lat;
    double log;
    boolean pressed;
    double devlat,devlong;
    protected LocationListener locationListener;
    protected LocationManager locationManager;



    TextView lumens;
    TextView humidity;
    TextView thermometer;
    TextView pressure;
    TextView battLevel;
    TextView statusbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        thermometer = (TextView) findViewById(R.id.tempView);
        thermometer.setBackgroundResource(R.drawable.thermometer);
        battLevel = (TextView) findViewById(R.id.batteryView);
        battLevel.setBackgroundResource(R.drawable.battery);
        humidity = (TextView) findViewById(R.id.humView);
        humidity.setBackgroundResource(R.drawable.humidity);
        lumens = (TextView) findViewById(R.id.lumView);
        lumens.setBackgroundResource(R.drawable.sun);
        pressure = (TextView) findViewById(R.id.pressView);
        pressure.setBackgroundResource(R.drawable.pressure);
        statusbar = (TextView) findViewById(R.id.saunaStatusBar);

        //ImageView cabin_Main = (ImageView) findViewById(R.id.cabinMain);
        //cabin_Main.setImageResource(R.drawable.cabin);
        configureMapButton();
        statusBarChange();
        mapVibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) { // coarse permission is granted
            // Todo
        } else { // permission is not granted, request for permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) { // show some info to user why you want this permission
                Toast.makeText(this, "Allow Location Permission to use this functionality.", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123 /*LOCATION_PERMISSION_REQUEST_CODE*/);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123 /*LOCATION_PERMISSION_REQUEST_CODE*/);
            }
        }
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

        pressed = false;
        new TalkToThingsee().execute("QueryState");

    }
    private void distCalc (){
        double distLat = lat - devlat;
        double distLong = log - devlong;
        double NS = (40000/360)*distLat;
        double EW = (20000/360)*distLong;
        double arclength = Math.sqrt(Math.pow(EW,2)+Math.pow(NS,2));
        String arc = String.valueOf(String.format("%.2f", arclength ) + "Km");
        TextView distance = (TextView)findViewById(R.id.distance);
        distance.setText(arc);
    }
    @Override
    public void onLocationChanged(Location location) {
        devlat = location.getLatitude();
        devlong = location.getLongitude();
        Log.d("Latitude","disable");

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider){
        Log.d("Latitude","disable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }



    private void statusBarChange ()
    {
        if (temp < 10)
        {
            statusbar.setText(R.string.gettingSaunaStatus);
            statusbar.setBackgroundResource(R.color.checking);
        }

        if (temp < 30 && temp > 11)
        {
            statusbar.setText(R.string.saunaOff);
            statusbar.setBackgroundResource(R.color.off);
        }
        if (temp >= 30 && temp <= 60)
        {
            statusbar.setText(R.string.saunaHeating);
            statusbar.setBackgroundResource(R.color.heating);
        }
        if (temp > 60)
        {
            statusbar.setText(R.string.saunaOn);
            statusbar.setBackgroundResource(R.color.on);
        }


    }

    public void setttingsButtonMethod (View view)
    {
        Button settingsButton = (Button) findViewById(R.id.settButton);
        Intent settingsIntent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(settingsIntent);

    }

    private void configureMapButton()
    {



        Button mapButton = (Button)findViewById(R.id.map);
        mapButton.setBackgroundResource(R.drawable.pin4);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapVibrator.vibrate(50);
                pressed = true;
                new TalkToThingsee().execute("QueryState");


            }
        });
    }

    private class TalkToThingsee extends AsyncTask<String, Integer, String> {


        ThingSee       thingsee;
        JSONObject     device;
        List<Location> coordinates = new ArrayList<Location>();


        @Override
        protected String doInBackground(String... params) {

            String result = "NOT OK";

            // first we authenticate with the server (we try three times)
            Random rand = new Random();
            thingsee = null;
            for (int i = 0; i < 3; i++) {
                try {
                    thingsee = new ThingSee(username, password);
                    break;
                } catch (Exception e) {
                    Log.d("NET", "Authentication error: " + e.getMessage());
                }
                try {
                    Thread.sleep(100 * (rand.nextInt(20)+1));   // random delay, 0.1 - 2s
                }
                catch(Exception e) {
                }
            }
            if (thingsee == null)
                return result;

            // then we make the request to the cloud server for MAXPOSITION number of coordinates
            try {
                thingsee = new ThingSee(username, password);

                device = thingsee.Devices();
               JSONArray  events = thingsee.Events(device, MAXPOSITIONS);
                //System.out.println(events);
                coordinates = thingsee.getPath(events);
                lum = (int) thingsee.getlum();
                pres = (int) thingsee.getpres();
                temp = (int) thingsee.gettemp();
                hum = (int) thingsee.gethum();
                bat = (int) thingsee.getbat();
                lat = thingsee.getLat();
                log = thingsee.getLog();



//                for (Location coordinate: coordinates
//               System.out.println(coordinates);
                result = "OK";
            } catch(Exception e) {
                Log.d("NET", "Communication error: " + e.getMessage());
            }
            return result;
        }



        @Override
        protected void onPostExecute(String result) {
            // check that the background communication with the client was succesfull
          if (result.equals("OK")) {
                // now the coordinates variable has those coordinates
                // elements of these coordinates is the Location object who has
                // fields for longitude, latitude and time when the position was fixed

              distCalc();

                    if(pressed == false)

                    {
                        lumens.setText(Integer.toString(lum)+" lm");
                        thermometer.setText(Integer.toString(temp)+" °C");
                        pressure.setText(Integer.toString(pres)+" hPa");
                        humidity.setText(Integer.toString(hum)+" %");
                        battLevel.setText(Integer.toString(bat)+" %");
                        statusBarChange();


                    }

                    if(pressed == true)

                    {

                        Intent myIntent = new Intent(HomeActivity.this, MapsActivity.class);

                        myIntent.putExtra("LATITUDE", lat);
                        myIntent.putExtra("LONGTITUDE", log);
                        myIntent.putExtra("DEVLONG",devlong);
                        myIntent.putExtra("DEVLAT",devlat);
                        startActivity(myIntent);



                        mapVibrator.vibrate(50);

                    }




            }


        }




        @Override
        protected void onPreExecute() {
             //first clear the previous entries (if they exist)
            for (int i = 0; i < positions.length; i++)
                positions[i] = "";

        }

        @Override
        protected void onProgressUpdate(Integer... values) {}

    }







}

