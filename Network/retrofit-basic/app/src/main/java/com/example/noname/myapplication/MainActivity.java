package com.example.noname.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.noname.myapplication.RetroFitClienteInstance.*;

public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Retrofit instance
        GetDataService dataService = RetroFitClienteInstance.getRetroFitStance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = dataService.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                generateDataList(response);
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void generateDataList(Response<List<RetroPhoto>> response) {
        Log.d("TAG :: ", String.valueOf(response.body().getClass()));



        for (RetroPhoto i : response.body()) {
            Log.d("TAG :: ", i.getTitle());
        }

    }


}


