package com.example.vagnercazarotto.retrofitguide;

import android.Manifest;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ToDoInBackground mClass = new ToDoInBackground();
                mClass.doInBackground();
            }

        });

        thread.start();




    }






}
