package com.tkcode.recap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // don't create a view before the view is populated 
        //setContentView(R.layout.activity_main);
        Log.i("Lifecycle","onCreate called");

        // Declare a array
        String[] countries = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_main,R.id.listV ,countries);
        this.setListAdapter(adapter);



    }

    protected void onStart(){
        super.onStart();
        Log.i("Lifecycle","onStart called");
    }

    protected void onResume(){
        super.onResume();
        Log.i("Lifecycle", "onResume called");
    }

    protected void onPause(){
        super.onPause();
        Log.i("Lifecycle", "onPause called");
    }

    protected void onStop(){
        super.onStop();
        Log.i("Lifecycle", "onStop called");
    }

    protected void onRestart(){
        super.onRestart();
        Log.i("Lifecycle", "onRestart called");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy called");
    }




    public void onButtonTouch(View view){
        startActivityTwo(view);
    }

    public void startActivityTwo(View view){
        // Create a intent to activity two. java
        Intent intent = new Intent(MainActivity.this,ActivityTwo.class);
        startActivity(intent);
    }
}
