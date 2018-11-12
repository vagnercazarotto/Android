package com.tkcode.maps3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class MainActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {

    private StreetViewPanoramaFragment streetViewPanoramaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a Street View panorama object
        StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment) getFragmentManager().findFragmentById(R.id.streetViewPanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }


    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(new LatLng(36.400546,-112.108668));
        streetViewPanorama.setStreetNamesEnabled(false);
        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder().bearing(180).build();
        streetViewPanorama.animateTo(camera,1000);
    }
}
