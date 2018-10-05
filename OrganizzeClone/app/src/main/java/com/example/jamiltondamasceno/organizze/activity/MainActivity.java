package com.example.jamiltondamasceno.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jamiltondamasceno.organizze.activity.CadastroActivity;
import com.example.jamiltondamasceno.organizze.activity.LoginActivity;
import com.example.jamiltondamasceno.organizze.config.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.example.jamiltondamasceno.organizze.R;


public class MainActivity extends IntroActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

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

    public void verificarUsuarioLogado(){
        firebaseAuth = ConfigFirebase.getFirebaseAuth();
        if(firebaseAuth.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }


}
