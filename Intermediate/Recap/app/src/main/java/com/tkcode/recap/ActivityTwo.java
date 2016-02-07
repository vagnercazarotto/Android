package com.tkcode.recap;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by vagner on 07/02/2016.
 */
public class ActivityTwo extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);
        Log.i("Second Activity", "Activity two Created");
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


