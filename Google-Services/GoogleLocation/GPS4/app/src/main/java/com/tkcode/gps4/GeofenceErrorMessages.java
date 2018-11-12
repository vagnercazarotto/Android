package com.tkcode.gps4;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.location.GeofenceStatusCodes;

/**
 * Created by vagner on 12/02/2016.
 */
public class GeofenceErrorMessages {

    public GeofenceErrorMessages() {
    }

    public static String getErrorString(Context context, int errorCode){
        Resources mResources = context.getResources();
        switch (errorCode){
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "Geofence service is not available now";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Your app has registered too many geofences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "You have provided too many PendingIntents to the addGeo ..";
            default:
                return "Unknown error: the Geofence service is not available now";
        }
    }
}
