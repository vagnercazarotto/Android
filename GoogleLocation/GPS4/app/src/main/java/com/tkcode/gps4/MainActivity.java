package com.tkcode.gps4;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener , ResultCallback<Status>{
    public static final String TAG = "Main Program";

    // static variables
    protected ArrayList<Geofence> mGeofenceList;
    protected GoogleApiClient mGoogleApiClient;
    private Button mAddGeofencesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddGeofencesButton = (Button) findViewById(R.id.add_geofences_button);

        // Empty list for storing geofences
        mGeofenceList = new ArrayList<Geofence>();

        // Get the Geofences used, Geofence data is hard coded in this sample
        populateGeofenceList();

        //kick of the request to build GoogleApiClient
        buildGoogleApiClient();

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
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    public void onResult(Status status){
        if (status.isSuccess()){
            Toast.makeText(this, "Geofence Add", Toast.LENGTH_SHORT).show();
        } else {
            // Get the status error and parse into user friendly message.
            String errorMessage = GeofenceErrorMessages.getErrorString(this,status.getStatusCode());
            Log.e(TAG,errorMessage);
        }
    }

    public PendingIntent getGeofencePendingIntent(){
        Intent intent = new Intent(this,GeofenceTransitionsIntentService.class);
        // we use FLAG UPDATE CURRENT so that we get same pending intent back calling addgeoFences();
        return PendingIntent.getService(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }



    public void addGeofencesButtonHandler(View view){
        if (!mGoogleApiClient.isConnected()){
            Toast.makeText(this,getString(R.string.not_connected),Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // A pending Intent that is reused when calling removeGeofences(), this pending intent
            // is used to generate an intent when a matched geofence transition is observed
            LocationServices.GeofencingApi.addGeofences(mGoogleApiClient, getGeofencingRequest(),
                    getGeofencePendingIntent()).setResultCallback((ResultCallback<? super Status>) this);
        } catch (SecurityException securityException){
            // Catch exception from the app does not use ACESS_FINE_LOCATION permission.
            logSecurityException(securityException);
        }

    }


    private void logSecurityException(SecurityException securityException){
        Log.e(TAG,"Invalid location permission",securityException);
    }


    // Uses the {@code #addApi} method to request the LocationServices API.
    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
