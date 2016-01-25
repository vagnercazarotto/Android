package com.example.android.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        // Declare variables
        ArrayAdapter<String> mForecastAdapter;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            // Now create some dummie data
            String[] forecastArray = {
                    "25/01 today Sunny",
                    "269/01 today Sunny",
                    "27/01 today Sunny",
                    "28/01 today Sunny",
                    "2/01 today Sunny",
                    "30/01 today Sunny"
            };
            // And convert as list
            List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));


            // The ArrayAdapter will take data from a source (like our dummy forecast) and
            // use it to populate the ListView it's attached to.
            mForecastAdapter = new ArrayAdapter<String>(
                    getActivity(),// this activity
                    R.layout.list_item_forecast,  // id of text view
                    R.id.list_item_forecast_textview, // id for populate
                    weekForecast); // A converted list

            // Get the reference to list view on activity_main.xml
            // then set the adpter to the view
            ListView listview =(ListView) rootView.findViewById(R.id.listview_forecast);
            listview.setAdapter(mForecastAdapter);


            return rootView;
        }
    }
}
