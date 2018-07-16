package com.example.noname.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetroFitClienteInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    public static Retrofit getRetroFitStance(){
        if(retrofit ==  null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    // Interface for the instance
    public interface GetDataService {
        @GET("/photos")
        Call<List<RetroPhoto>> getAllPhotos();
    }
}
