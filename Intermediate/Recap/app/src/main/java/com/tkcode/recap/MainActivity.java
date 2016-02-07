package com.tkcode.recap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Lifecycle","onCreate called");
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
