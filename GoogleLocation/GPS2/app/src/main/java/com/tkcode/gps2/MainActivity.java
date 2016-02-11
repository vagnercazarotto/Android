package com.tkcode.gps2;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks ,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    // declare some variables
    protected static final String TAG = "GPS2 TAG:";
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    private TextView mLatitudeText;
    private TextView mLongitudeText;
    protected LocationRequest mLocationRequest;

    @Override
    protected void onStop() {
        super.onStop();
        // disconnect google API
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /// We need to initiate the Text View and call the google API
        mLatitudeText = (TextView) findViewById(R.id.latitude_text);
        mLongitudeText = (TextView) findViewById(R.id.longitude_text);
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect the API
        mGoogleApiClient.connect();
    }

    protected synchronized void buildGoogleApiClient() {
        // build the google API connection
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection Failed ERROR: " + connectionResult.getErrorCode());
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }


    // Overrider Listener Methods

    public void onDisconnected() {
        Log.i(TAG, "Disconnected");
    }

    @Override
    public void onLocationChanged(Location location) {
            Log.i(TAG + "Location changed", location.toString());
            mLatitudeText.setText(Double.toString(location.getLatitude()));
            mLongitudeText.setText(Double.toString(location.getLatitude()));
    }

}
