package com.example.vagnercazarotto.recyclerviewreview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    final RecyclerView mRecyclerView = findViewById(R.id.myRecyclerView);
    final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
