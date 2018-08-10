package com.example.vagnercazarotto.retrofitguide;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ToDoInBackground extends AsyncTask<Context, Void, String>{
    public static final String API_URL = "https://api.github.com";


    @Override
    protected String doInBackground(Context... contexts) {

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create an instance of our GitHubApi interface
        GitHub gitHub = retrofit.create(GitHub.class);

        // create a call instance for looking up Retrofit contributors
        Call<List<Contributor>> call = gitHub.contributions("square","retrofit");

        // Fetch and print a list fo the contributos to the lib
        try {
            List<Contributor> contributors = call.execute().body();
            for (Contributor contributor : contributors){
                Log.d("TAGG","name: "+ contributor.login + " quantidade: " + contributor.contributions +"\n");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}