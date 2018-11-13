package com.example.utente.asynctask.api;

import com.example.utente.asynctask.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CEPService {

    @GET("01001000/json")
    Call<CEP> recuperarCEP();

}
