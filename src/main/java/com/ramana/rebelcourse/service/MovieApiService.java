package com.ramana.rebelcourse.service;

import com.ramana.rebelcourse.payload.response.BaseResponse;
import com.ramana.rebelcourse.repository.MovieApiRepository;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public interface MovieApiService {

    BaseResponse<Object> requestTopMovies();
}
