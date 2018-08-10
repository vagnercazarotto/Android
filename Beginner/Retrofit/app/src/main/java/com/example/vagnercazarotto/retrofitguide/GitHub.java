package com.example.vagnercazarotto.retrofitguide;



import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHub {

    @GET("/repos/{owner}/{repo}/contributors")
    retrofit2.Call<List<Contributor>> contributions(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
