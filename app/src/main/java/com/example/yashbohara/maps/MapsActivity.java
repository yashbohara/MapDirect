package com.example.yashbohara.maps;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import static com.example.yashbohara.maps.Main2Activity.a;
import static com.example.yashbohara.maps.Main2Activity.b;
import static com.example.yashbohara.maps.MainActivity.*;
import static com.example.yashbohara.maps.MainActivity.arr2;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(a, b);
        float zoomlevel = 18;
        mMap.addMarker(new MarkerOptions().position(new LatLng(a, b)).title("Destination"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(lat1, lng1)).title("Source"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomlevel));
        mMap.addPolyline(new PolylineOptions().add(
                new LatLng(arr1[0], arr2[0]),
                new LatLng(arr3[0], arr4[0])
        ).width(10).color(Color.RED));
        mMap.addMarker(new MarkerOptions().position(new LatLng(arr1[0], arr2[0])).title("ssss11" + arr1[0] + "," + arr2[0]));
        mMap.addMarker(new MarkerOptions().position(new LatLng(arr3[0], arr4[0])).title("ssss22" + arr3[0] + "," + arr4[0]));
        mMap.addPolyline(new PolylineOptions().add(
                new LatLng(lat1, lng1),
                new LatLng(arr1[0], arr2[0])
        ).width(10).color(Color.RED));
        mMap.addMarker(new MarkerOptions().position(new LatLng(arr1[0], arr2[0])).title("ssss11" + arr1[0] + "," + arr2[0]));
        mMap.addMarker(new MarkerOptions().position(new LatLng(arr3[0], arr4[0])).title("ssss22" + arr3[0] + "," + arr4[0]));
        for (i = 0; i < arr3.length - 1; i++) {
            double t = arr3[i] - a;
            double t1 = arr4[i] - b;
            double t3 = t1 + t;
           /*if(t<0)
           {
               t=t*-1;
           }if(t1<0)
           {
               t1=t1*-1;
           }*/
            //if(t>0.0001&&t1>0.0001){
            if (t3 < 0) {
                t3 = t3 * -1;
            }
            if (t3 > 0.0001) {
                mMap.addPolyline(new PolylineOptions().add(
                        new LatLng(arr3[i], arr4[i]),
                        new LatLng(arr3[i + 1], arr4[i + 1])
                ).width(10).color(Color.RED));
                mMap.addMarker(new MarkerOptions().position(new LatLng(arr3[i], arr4[i])).title("" + arr3[i] + "," + arr4[i]));
                mMap.addMarker(new MarkerOptions().position(new LatLng(arr3[i + 1], arr4[i + 1])).title("" + arr3[i + 1] + "," + arr4[i + 1]));
            } else {
                mMap.addPolyline(new PolylineOptions().add(new LatLng(arr3[i], arr4[i]), latLng).width(10).color(Color.RED));
                break;
            }

        }
        if (i == arr3.length - 1) {
            mMap.addPolyline(new PolylineOptions().add(new LatLng(arr3[i], arr4[i]), latLng).width(10).color(Color.RED));
        }
/*

        mMap.addPolyline(new PolylineOptions().add(
                new LatLng(arr1[arr1.length-1],arr2[arr1.length-1]),
                new LatLng(arr3[arr3.length-1],arr4[arr3.length-1])
        ).width(10).color(Color.RED));
        mMap.addMarker(new MarkerOptions().position(new LatLng(arr1[arr1.length-1],arr2[arr1.length-1])).title("dddd11"+arr1[arr1.length-1]+","+arr2[arr1.length-1]));
        mMap.addMarker(new MarkerOptions().position(new LatLng(arr1[arr1.length-1],arr2[arr1.length-1])).title("dddd22"+arr3[arr3.length-1]+","+arr4[arr3.length-1]));
*/

    }
}
