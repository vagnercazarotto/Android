package com.example.utente.asynctask.api;

import com.example.utente.asynctask.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json")
    Call<CEP> recuperarCEP(@Path("cep") String cep);

}
