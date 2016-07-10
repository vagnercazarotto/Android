package com.example.vagner.bannderad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.example.vagner.bannderad.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    private AdLayout bannerAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        AdRegistration.setAppKey("d2f251f30eb346b8ab00c8be1cd0f072");

        // create our banner ad
        this.bannerAd = new AdLayout(this);

        //define a relative layout
        RelativeLayout layout = new RelativeLayout(this);

        // add ours views to our layout
        layout.addView(this, MATCH_PARENT, MATCH_PARENT);
        //banner ad
        RelativeLayout.LayoutParams bannerDimensions = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,WRAP_CONTENT);

        layout.addView(bannerAd, bannerDimensions);

        //create the new view
        setContentView(layout);

        // load our ad
        bannerAd.loadAd()
    }
}

