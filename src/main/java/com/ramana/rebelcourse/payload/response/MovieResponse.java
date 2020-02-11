package com.ramana.rebelcourse.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieResponse {

    @JsonIgnore
    private String imageHost = "https://image.tmdb.org/t/p/w500";

    @JsonProperty(value = "poster_path")
    private String posterPath;

    @JsonProperty(value = "original_title")
    private String originalTitle;

    @JsonProperty(value = "overview")
    private String overview;

    @JsonProperty(value = "release_date")
    private String releaseDate;

    @JsonProperty(value = "vote_average")
    private Double voteAverage;

    public MovieResponse() {
    }

    public MovieResponse(Result movie) {
        setPosterPath(imageHost + movie.getPosterPath());
        setOriginalTitle(movie.getOriginalTitle());
        setOverview(movie.getOverview());
        setReleaseDate(movie.getReleaseDate());
        setVoteAverage(movie.getVoteAverage());
    }
}
