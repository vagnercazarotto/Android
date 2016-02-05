package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
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

        return super.onOptionsItemSelected(item);
    }

    public static class DetailFragment extends Fragment {
        //// Let's add a log Tag, a string for the share hashtag and take the forecast
        //// string and make it a member variable
        private static final String LOG_TAG = DetailFragment.class.getSimpleName();
        private static final String FORECAST_SHARE_HASHTAG = "#SunshineApp";
        private String mForecastStr;

        public DetailFragment(){
            /// We have to add the menu to the fragment. To do this, we have to set a flag
            /// that this fragment has an option menu at all. Otherwise, it won't actually call
            /// the onCreate option menu member function.
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            // So, if we add an intent here, we can read that intent back to display in the text view
            Intent intent = getActivity().getIntent();

            //// let's actually populate our member variable, and then finally, use it to set the text
            if(intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
                 mForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById(R.id.detail_text)).setText(mForecastStr);
            }

            return rootView;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
            // Inflate the menu; this adds items to the action bar if it is present.
            inflater.inflate(R.menu.detailfragment,menu);

            // Find the share item and start a Menu item
            MenuItem menuItem = menu.findItem(R.id.action_share);

            // Get the provider (ShareActionProvider)
            ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

            // You'll want to update this. Attach an intent to this ShareActionProvider. You can update this any time,
            // like when the user selects a new piece of data they might like to share.
            if (mShareActionProvider != null){
                mShareActionProvider.setShareIntent(createShareForecastIntent());
            } else {
                Log.d(LOG_TAG,"Share Action Provider is null?");
            }
        }


        //////////////////////////////////////
        //// Ok, now we'll create a Share Intent, this intent uses ACTION_SEND
        private Intent createShareForecastIntent(){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            //// this FLAG_RESET is somewhat important, it prevents the activity we're sharing
            //// to from being placed onto the activity stack. the thing is if we don't have this flag,
            //// is when you click on the icon to return the application later, you end up in another application !!
            //// Now it'll actually return you to your application instead.
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            //// So now we set the type "test/plain", to let Android know we're going to be sharing plain text.
            shareIntent.setType("text/plain");
            ////  And then whe ahre our forecast string, plus our hashtag.
            shareIntent.putExtra(Intent.EXTRA_TEXT,mForecastStr + FORECAST_SHARE_HASHTAG);
            return shareIntent;
        }


    }

}
