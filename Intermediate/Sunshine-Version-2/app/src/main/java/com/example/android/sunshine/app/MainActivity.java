package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    /////// Start global variables
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            // When the Menu Item is selected, we create a new explicit Intent
            // to the SettingsActivity Class and we call Start Activity
            // Obs: we don't call Start Activity for results because we're not expecting a result from the Activity
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }

        //// conditions to call the Intent MAP
        if (id == R.id.action_map){
            openPreferredLocationInMap();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    ////////////////////////////////////////
    /// new method for call open preferred location in map
    private void openPreferredLocationInMap(){
        //// Read the user preferences
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPrefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));

        //// We should build a link to invoke the location intent, reference: http://developer.android.com/guide/components/intents-common.html#Maps
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon().appendQueryParameter("q",location).build();

        //// Then we create a view intent, indicating its location in the data URI
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        //// we need to start a Activity, but only do this if the Activity resolves successfully
        if (intent.resolveActivity(getPackageManager())!= null ){
            startActivity(intent);
        }  else {
            Log.d(LOG_TAG,"Couldn't call " + location + ", no receiving apps installed!!");
        }
    }

    ////////////////////////////////////////
}



