package com.ramana.rebelcourse.repository;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiRepository {

    @GET("3/discover/movie")
    Call<ResponseBody> requestTopMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
