package com.example.handinapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    String apiKey = "?api_key=8435405a7811e77096b457192b4ea205";
    String languageEN = "&language=en-US";
    String adultFalse = "&include_adult=false";

    @GET("/3/movie/{movie_id}")
    Call<Movie> getMovieDetail(@Path("movie_id") int id);

    @GET("/3/movie/popular" + apiKey + languageEN)
    Call<MovieResponseList> getPopularMovies();

    @GET("/3/search/movie" + apiKey + languageEN)
    Call<MovieResponseList> searchMovieByTitle(@Query("query") String title);

}
