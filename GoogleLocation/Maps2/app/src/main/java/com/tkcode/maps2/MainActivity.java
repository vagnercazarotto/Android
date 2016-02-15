package com.tkcode.maps2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    // Variables
    GoogleMap m_map;
    boolean mapReady=false;
    LatLng renton=new LatLng(47.489805, -122.120502);
    LatLng kirkland=new LatLng(47.7301986, -122.1768858);
    LatLng everett=new LatLng(47.978748,-122.202001);
    LatLng lynnwood=new LatLng(47.819533,-122.32288);
    LatLng montlake=new LatLng(47.7973733,-122.3281771);
    LatLng kent=new LatLng(47.385938,-122.258212);
    LatLng showare=new LatLng(47.38702,-122.23986);

    // Default Camera position for start a program
    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204,-122.2491))
            .zoom(9)
            .bearing(0)
            .tilt(45)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(SEATTLE));
        googleMap.addCircle(new CircleOptions()
            .center(renton)
                .radius(5000)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(64,0,255,0)));


        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(renton)
                .add(kirkland)
                .add(everett)
                .add(montlake)
                .add(kent)
                .add(showare)
                .add(renton));
    }

}
