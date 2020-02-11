package com.ramana.rebelcourse.controller;

import com.ramana.rebelcourse.payload.response.BaseResponse;
import com.ramana.rebelcourse.service.MovieApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieApiService movieApiService;

    @GetMapping(value = "/all", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> topMovies() {
        BaseResponse<Object> response = movieApiService.requestTopMovies();
        return ResponseEntity.ok(response);
    }

}