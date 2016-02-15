package com.tkcode.maps2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    // Variables
    GoogleMap m_map;
    boolean mapReady=false;
    MarkerOptions renton;
    MarkerOptions kirkland;
    MarkerOptions everett;
    MarkerOptions lynnwood;
    MarkerOptions montlake;
    MarkerOptions kent;
    MarkerOptions showare;

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

        // Create the Marks
        renton = new MarkerOptions()
                .position(new LatLng(47.489805,-122.120502))
                .title("Renton");

        kirkland = new MarkerOptions()
                .position(new LatLng(47.7301986, -122.1768858))
                .title("Kirkland");

        everett = new MarkerOptions()
                .position(new LatLng(47.978748,-122.202001))
                .title("Everett");


        lynnwood = new MarkerOptions()
                .position(new LatLng(47.819533,-122.32288))
                .title("Lynnwood");


        montlake = new MarkerOptions()
                .position(new LatLng(47.7973733,-122.3281771))
                .title("Montlake Terrace");


        kent = new MarkerOptions()
                .position(new LatLng(47.385938,-122.258212))
                .title("Kent Valley");


        showare = new MarkerOptions()
                .position(new LatLng(47.38702,-122.23986))
                .title("Showare Center");

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // when the map is ready we're gonna add the marks
        mapReady=true;
        m_map = googleMap;
        m_map.addMarker(renton);
        m_map.addMarker(kirkland);
        m_map.addMarker(everett);
        m_map.addMarker(lynnwood);
        m_map.addMarker(montlake);
        m_map.addMarker(kent);
        m_map.addMarker(showare);
        // then got o default position
        flyTo(SEATTLE);
    }

    private void flyTo(CameraPosition cameraPosition){
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }



}
