package com.tkcode.gps3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // Create fix variables
    protected ActivityDetectionBroadcastReceiver mBroadcastReceiver;
    protected GoogleApiClient mGoogleApiClient;
    private TextView mStatusText;
    private Button requestUpdatesButton;
    private Button removeUpdatesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestUpdatesButton = (Button) findViewById(R.id.request_activity_updates_button);
        removeUpdatesButton = (Button) findViewById(R.id.remove_activity_updates_button);
        mStatusText = (TextView) findViewById(R.id.detectedActivities);
        mBroadcastReceiver  = new ActivityDetectionBroadcastReceiver();
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(ActivityRecognition.API)
                .build();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnected(Bundle bundle) {

    }


    public String getActivityString(int detectedActivityType){
        Resources resources = this.getResources();
        switch (detectedActivityType){
            case DetectedActivity.IN_VEHICLE:
                return resources.getString(R.string.in_vehicle);
            case DetectedActivity.ON_BICYCLE:
                return resources.getString(R.string.on_bicycle);
            case DetectedActivity.ON_FOOT:
                return resources.getString(R.string.on_foot);
            case DetectedActivity.RUNNING:
                return resources.getString(R.string.running);
            case DetectedActivity.STILL:
                return resources.getString(R.string.still);
            case DetectedActivity.TILTING:
                return resources.getString(R.string.tilting);
            case DetectedActivity.UNKNOWN:
                return resources.getString(R.string.unknown);
            case DetectedActivity.WALKING:
                return resources.getString(R.string.walking);
            default:
                return resources.getString(R.string.unidentifiable_activity);
        }
    }


    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver{
        protected static final String TAG = "RECEIVER";


        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<DetectedActivity> updateActivities = intent.getParcelableArrayListExtra(Constants.ACTIVITY_EXTRA);
            String strStatus = "";
            for (DetectedActivity thisActivity: updateActivities){
                strStatus += getActivityString(thisActivity.getType()) + thisActivity.getConfidence() + "%\n" ;
            }
            mStatusText.setText(strStatus);
        }
    }

}
