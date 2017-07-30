package com.example.yashbohara.maps;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.yashbohara.maps.Main2Activity.a;
import static com.example.yashbohara.maps.Main2Activity.b;
import static com.example.yashbohara.maps.MainActivity.lat1;
import static com.example.yashbohara.maps.MainActivity.lng1;

public class fragmentone extends android.support.v4.app.Fragment implements OnMapReadyCallback {
    MapView mapView;
    GoogleMap googleMap1;
   public fragmentone() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragone, container, false);
        mapView =(MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        MapsInitializer.initialize(getActivity().getApplicationContext());
        if (mapView != null) {
            mapView.getMapAsync(this);
        }
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap1 = googleMap;
        googleMap1.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap1.setMyLocationEnabled(true);
        googleMap1.setTrafficEnabled(true);
        googleMap1.setIndoorEnabled(true);
        googleMap1.setBuildingsEnabled(true);
        googleMap1.getUiSettings().setZoomControlsEnabled(true);
        LatLng latLng = new LatLng(lat1,lng1);
        googleMap1.addMarker(new MarkerOptions().position(new LatLng(lat1,lng1)).title("First Marker"));
        float zoomlevel=16;
        googleMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoomlevel));
    }
}
