package com.example.utente.asynctask.api;



import com.example.utente.asynctask.model.Foto;
import com.example.utente.asynctask.model.post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<post>> recuperarPostagens();




}
