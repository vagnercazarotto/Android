package com.example.jamiltondamasceno.organizze;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new SimpleSlide.Builder()
                .title("My Title")
                .description("My description..")
                .image(R.drawable.um)
                .background(android.R.color.holo_orange_light)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("My Title2")
                .description("My description..2")
                .image(R.drawable.dois)
                .background(android.R.color.holo_orange_light)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("My Title3")
                .description("My description..3")
                .image(R.drawable.tres)
                .background(android.R.color.holo_orange_light)
                .build());


    }
}
