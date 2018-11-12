package com.example.vagner.amazonapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.amazon.device.ads.DefaultAdListener;
import com.amazon.device.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up our app key
        AdRegistration.setAppKey("d2f251f30eb346b8ab00c8be1cd0f072");

        //create our add
        this.interstitialAd = new InterstitialAd(this);


        // load out add
        interstitialAd.loadAd();


        // add listener
        this.interstitialAd.setListener(new MyCustomListener());

    }




    class MyCustomListener extends DefaultAdListener{
        @Override
        public void onAdLoaded(Ad ad, AdProperties adProperties) {
            super.onAdLoaded(ad, adProperties);
            // show the ad
            if (ad == MainActivity.this.interstitialAd)
            {
                // Show the interstitial ad to the app's user.
                // Note: While this implementation results in the ad being shown
                // immediately after it has finished loading, for smoothest user
                // experience you will generally want the ad already loaded
                // before it’s time to show it. You can thus instead set a flag
                // here to indicate the ad is ready to show and then wait until
                // the best time to display the ad before calling showAd().
                MainActivity.this.interstitialAd.showAd();
            }
        }

        @Override
        public void onAdFailedToLoad(Ad ad, AdError error) {
            super.onAdFailedToLoad(ad, error);
            // call a backup ad network
        }

        @Override
        public void onAdDismissed(Ad ad) {
            super.onAdDismissed(ad);
            // resume our game
            // Start the level once the interstitial has disappeared.

        }
    }


}
