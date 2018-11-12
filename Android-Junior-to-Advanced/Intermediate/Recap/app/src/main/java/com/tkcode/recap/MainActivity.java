package com.tkcode.recap;

import android.widget.AdapterView.OnItemClickListener;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // don't create a view before the view is populated
        //setContentView(R.layout.activity_main);
        Log.i("Lifecycle","onCreate called");

        // Declare a array
        final String[] countries = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_main,R.id.listV ,countries);
        this.setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View clickView,int position, long id){
                String country = countries[position];
                Toast.makeText(MainActivity.this,String.format("%s was chosen.",country),Toast.LENGTH_LONG).show();
            }
        });






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
