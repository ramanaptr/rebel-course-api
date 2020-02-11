package com.ramana.rebelcourse.service;

import com.google.gson.Gson;
import com.ramana.rebelcourse.payload.response.BaseResponse;
import com.ramana.rebelcourse.payload.response.MovieApiResponse;
import com.ramana.rebelcourse.payload.response.MovieResponse;
import com.ramana.rebelcourse.payload.response.Result;
import com.ramana.rebelcourse.repository.MovieApiRepository;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieApiServiceImpl implements MovieApiService {

    @Value("${api.host}")
    private String host;

    @Value("${api.token}")
    private String token;

    @Value("${api.language.en}")
    private String en;

    @Value("${api.language.id}")
    private String id;

    private MovieApiRepository service;

    @PostConstruct
    public void init() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(MovieApiRepository.class);
    }

    @Override
    public BaseResponse<Object> requestTopMovies() {
        try {
            Call<ResponseBody> responseBodyCall = service.requestTopMovies(token, en);
            Response<ResponseBody> response = responseBodyCall.execute();

            if (!response.isSuccessful()) BaseResponse.setAsFailed("Failed to get data");

            if (response.body() == null) return BaseResponse.setAsFailed("Failed get data");

            String json = response.body().string();
            MovieApiResponse movieApiResponse = new Gson().fromJson(json, MovieApiResponse.class);
            List<MovieResponse> movies = new ArrayList<>();

            for (Result movie : movieApiResponse.getResults()){
                movies.add(new MovieResponse(movie));
            }

            return BaseResponse.setAsSuccess(movies);
        } catch (Exception e) {
            return BaseResponse.setAsFailed(e);
        }
    }
}
