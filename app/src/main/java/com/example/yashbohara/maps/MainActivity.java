package com.example.yashbohara.maps;

import android.Manifest;
import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements LocationListener {
    Button b1, b2, b3, b4, b5, nearby, placesearch;
    static double arr1[], arr2[], arr3[], arr4[];
    static EditText e3, e4;
    public static String pq;
    static ProgressDialog progressDialog, progressDialog1;
    static String s2 = "";
    static String st, st2;
    Location location;
    static int flag = 0;
    static double lat1, lng1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("hello", "57");
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        if (locationManager != null) {
            location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        Log.e("hello", "68");
        if (location != null) {
            lat1 = location.getLatitude();
            lng1 = location.getLongitude();
            Log.e("hello", "68" + location.getLatitude());
            Log.e("hello", "68" + location.getLongitude());
        }
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.button2);
        nearby = (Button) findViewById(R.id.button3);
        placesearch = (Button) findViewById(R.id.button4);

        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new Fragone();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentlayout, fragment);
                fragmentTransaction.commit();
            }
        });
        placesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmenttwo fragment2 = new fragmenttwo();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentlayout, fragment2);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("hello", "145");
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}