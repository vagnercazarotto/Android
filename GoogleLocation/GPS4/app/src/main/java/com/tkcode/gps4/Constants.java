package com.tkcode.gps4;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by vagner on 13/02/16.
 */
public final class Constants {
    public Constants() {
    }

    // Fix variables

    public static final String PACKAGE_NAME = "com.google.android.gms.location.Geofence";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";



    //set an expiration time for a geofence. After this amount of time Location Services stops tracking the geofence.
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 2;
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 15;





    // Map for storing information about the city
    public static final HashMap<String,LatLng> NEWCASTLE_AREA_LANDMARKS = new HashMap<String,LatLng>();

    static {

        NEWCASTLE_AREA_LANDMARKS.put("NEW",new LatLng(55.0024505,-1.7268832));

        NEWCASTLE_AREA_LANDMARKS.put("BEER_SHOP", new LatLng(54.9756159,-1.6535447));

        NEWCASTLE_AREA_LANDMARKS.put("HOME",new LatLng(54.9746701,-1.6539804));

    }



}
