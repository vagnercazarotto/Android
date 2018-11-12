package com.tkcode.gps3;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by vagner on 11/02/2016.
 */
public class DetectedActivitiesIntentService  extends IntentService{
    protected static final String TAG = "DETECTION IS: ";

    public DetectedActivitiesIntentService() {
        super(TAG);
    }

    // Default overrider
    @Override
    protected void onHandleIntent(Intent intent) {
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION);
        ArrayList<DetectedActivity> detectedActivities = (ArrayList) result.getProbableActivities();
        Log.i(TAG,"ACTIVITIES DETECTED");
        // broadcast the list of detected activities
        localIntent.putExtra(Constants.ACTIVITY_EXTRA,detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
