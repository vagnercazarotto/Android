package com.tkcode.recap;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

/**
 * Created by vagner on 07/02/2016.
 */
public class ActivityTwo extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);
        Log.i("Second Activity", "Activity two Created");

        // Declare a array
        String[] countries = getResources().getStringArray(R.array.countries);
        this.setListAdapter(new ArrayAdapter<String>(this,R.layout.secondlayout,R.id.textView2,countries));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Second Activity", "Activity two Started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Second Activity", "Activity two Stopped");
    }
}


