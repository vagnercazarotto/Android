package com.example.jamiltondamasceno.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jamiltondamasceno.organizze.activity.CadastroActivity;
import com.example.jamiltondamasceno.organizze.activity.LoginActivity;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.example.jamiltondamasceno.organizze.R;


public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addSlide(new FragmentSlide.Builder()
            .background(android.R.color.white)
            .fragment(R.layout.intro_01)
            .canGoBackward(false)
            .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_02)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_03)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_04)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build());

    }

    public void btnEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btnCadastrar(View view){
        startActivity(new Intent(this, CadastroActivity.class));
    }


}
