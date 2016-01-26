package com.example.android.sunshine.app;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

    public ForecastFragment() {
    }

    /////////////////////////////////////////////////////////
    /// for add and inflate the menu, you need to override some methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Activate the menu
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.forecastfragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //alter for the buttom refresh
        if (id == R.id.action_refresh) {
            // Build a URL from the postal code to consult the weather
            FetchWeatherTask weatherTask = new FetchWeatherTask();
            weatherTask.execute("94043");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /////////////////////////////////////////////////////////

    // Declare variables
    ArrayAdapter<String> mForecastAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Now create some dummie data
        String[] data = {
                "25/01 today Sunny",
                "269/01 today Sunny",
                "27/01 today Sunny",
                "28/01 today Sunny",
                "2/01 today Sunny",
                "30/01 today Sunny"
        };
        // And convert as list
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(data));


        // The ArrayAdapter will take data from a source (like our dummy forecast) and
        // use it to populate the ListView it's attached to.
        mForecastAdapter = new ArrayAdapter<String>(
                getActivity(),// this activity
                R.layout.list_item_forecast,  // id of text view
                R.id.list_item_forecast_textview, // id for populate
                weekForecast); // A converted list


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Get the reference to list view on activity_main.xml
        // then set the adpter to the view
        ListView listview = (ListView) rootView.findViewById(R.id.listview_forecast);
        listview.setAdapter(mForecastAdapter);

        ///// Here I'll add Toast for debug purpose, Toast is a visual interaction (pop-up)
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // this is for display the TOAST
                String forecast = mForecastAdapter.getItem(position);
                /// Toast.makeText(getActivity(),forecast,Toast.LENGTH_SHORT).show();
                // Now I'll replace the Toas Method for a Intent*
                // we can use an intent extra, but we can use any string for the for the key.
                // Obs: we can't change the key when we receive the data.
                // we obtain the data calling get item on the Adapter
                Intent intent = new Intent(getActivity(),DetailActivity.class)
                        .putExtra(Intent.EXTRA_TEXT,forecast);
                // then we just start the Activity!!
                startActivity(intent);
            }
        });

        return rootView;
    }


    ////// Implement class for the http request in background
    //
    ///// OK remember you should observe and modify the return type in backgrund
    public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
        //////////////////////////////////
        /////// Add functions for manipulate Json Object
        /////// These functions should run in background ( asynctask )

        private String getReadableDateString(Long time){
            // So the API is returning timestamp, we need to convert to milliseconds for fix the date
            SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
            return shortenedDateFormat.format(time);
        }

        private String formatHighLows(double high,double low){
            //Build a String High / low to show
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);
            String highLowStr = roundedHigh + "/" + roundedLow;
            return highLowStr;
        }

        private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays) throws JSONException{
            // Take a initial string from json and filter the data we will use.

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_LIST = "list";
            final String OWM_WEATHER = "weather";
            final String OWM_TEMPERATURE = "temp";
            final String OWM_MAX = "max";
            final String OWM_MIN = "min";
            final String OWM_DESCRIPTION = "main";

            // Constructor takes the JSON and convert it into a Object hierarchy
            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            // API returns daily forecasts based upon the local time of the city.
            // for note: the first day is always the current day !!

            Time dayTime = new Time();
            dayTime.setToNow();

            // we need to fix the local day to start working
            int julianStartDay = Time.getJulianDay(System.currentTimeMillis(),dayTime.gmtoff);

            // we work exclusively in UTC
            dayTime = new Time();

            String[] resultStrs = new String[numDays];
            for(int i = 0; i < weatherArray.length();i++){
                // Using the format "Day , description , hi/low"
                String day;
                String description;
                String highAndLow;

                // get the JSON object who represents the day
                JSONObject dayForecast = weatherArray.getJSONObject(i);

                // we should convert dateTime in something human-readable
                // because most people won't read "1400356800" and say "OK is friday"
                long dateTime;
                dateTime = dayTime.setJulianDay(julianStartDay + i);
                day = getReadableDateString(dateTime);

                // get this , description is in a child array called "weather", which is 1 element long.
                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                description = weatherObject.getString(OWM_DESCRIPTION);

                JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
                double high = temperatureObject.getDouble(OWM_MAX);
                double low = temperatureObject.getDouble(OWM_MIN);

                highAndLow = formatHighLows(high,low);


                // Now build a Array of results
                resultStrs[i] = day + " - " + description + " - " + highAndLow;
            }

            /// Activate this for debug Jason Parser
            for(String s: resultStrs){
                Log.v(LOG_TAG," Forecast Entry: " + s);
            }

            // Now return a Array of results
            return resultStrs;
        }




        //////////////////////////////////

        //remember override class

        @Override
        protected String[] doInBackground(String... params) {


            ///////////////////////////////////////////////////////////////////


            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String forecastJsonStr = null;

            /////////////////////////////////
            ///// Build a URL contructor
            String format = "json";
            String units = "metric";
            String APPID = "36116643d79e40468b18defba69090aa";
            int numDays = 7;

            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast

                // http://openweathermap.org/API#forecast
                // we will exclude this soon.
                URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metrica&cnt=7&APPID=36116643d79e40468b18defba69090aa");




                final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
                final String QUERY_PARAM = "q";
                final String FORMAT_PARAM = "mode";
                final String UNITS_PARAM = "units";
                final String DAYS_PARAM = "cnt";
                final String KEY= "APPID";

                Uri buildUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(QUERY_PARAM,params[0])
                        .appendQueryParameter(FORMAT_PARAM,format)
                        .appendQueryParameter(UNITS_PARAM,units)
                        .appendQueryParameter(DAYS_PARAM,Integer.toString(numDays))
                        .appendQueryParameter(KEY,APPID)
                        .build();

                // Now parse into log

                Log.v(LOG_TAG,"Built URI" + buildUri.toString());

                /////////////////////////////////
                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                forecastJsonStr = buffer.toString();

                // Activate log for the internet connection
                Log.v(LOG_TAG,"Forest Jason String: " + forecastJsonStr);

            } catch (IOException e) {
                Log.e("ForecastFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("ForecastFragment", "Error closing stream", e);
                    }
                }
            }
            //////////////////////////////////////////////////////////////////////
            // Ok Activate Jason interaction
            try{
                return getWeatherDataFromJson(forecastJsonStr,numDays);
            }catch (JSONException e){
                Log.e(LOG_TAG,e.getMessage(),e);
                e.printStackTrace();
            }
            //////////////////////////////////////////////////////////////////////
            return null;
        }

        @Override
        protected void onPostExecute(String[] result){
            // Override this method for populate the Adapter with fresh Data from JSON API
            if(result != null){
                mForecastAdapter.clear();
                for(String dayForecastStr:result){
                    mForecastAdapter.add(dayForecastStr);
                }
            }

        }

    }

}