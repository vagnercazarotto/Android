package com.tkcode.gps4;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // static variables
    protected ArrayList<Geofence> mGeofenceList;
    protected GoogleApiClient mGoogleApiClient;
    private Button mAddGeofencesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void populateGeofenceList(){
        for (Map.Entry<String,LatLng> entry : Constants.NEWCASTLE_AREA_LANDMARKS.entrySet()){

            mGeofenceList.add(new Geofence.Builder()
                    .setRequestId(entry.getKey())     //set the request ID anf then set the circular region
                    .setCircularRegion(entry.getValue().latitude,entry.getValue().longitude,Constants.GEOFENCE_RADIUS_IN_METERS)
                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                    .build());
        }
    }



    // Create a geofence request
    private GeofencingRequest getGeofencingRequest(){
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();

        // only activate the geofance when the device is in the local of the geofence
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        // Add the Geofences to be monitored by Geofencing service.
        builder.addGeofences(mGeofenceList);
        // Return a GeofencingRequest
        return builder.build(); 
    }

    private PendingIntent getPendingIntent(){
        Intent intent = new Intent(this,GeofenceTransitionsIntentService.class);
        // we use FLAG_UPDATE_CORRENT so that we get the same pending intent back when calling addGeofences()
        return PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }





}
