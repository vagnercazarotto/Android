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

    public static final String API_URL = "https://api.github.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create an instance of our GitHubApi interface
        GitHub gitHub = retrofit.create(GitHub.class);

        // create a call instance for looking up Retrofit contributors
        final Call<List<Contributor>> call = gitHub.contributions("square","retrofit");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Fetch and print a list fo the contributos to the lib
                List<Contributor> contributors = null;
                try {
                    contributors = call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (Contributor contributor : contributors){
                    Log.d("TAGG","user: "+ contributor.login + " counter: " + contributor.contributions +"\n");
                }
            }

        });

        thread.start();

    }

}
