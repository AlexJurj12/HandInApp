package com.example.handinapp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    String apiKey = "?api_key=8435405a7811e77096b457192b4ea205";
    String languageEN = "&language=en-US";

    @GET("movie/{movie_id}" + apiKey + languageEN)
    Call<MovieResponse> searchMovieById(@Path("movie_id") int id);

    @GET("movie/popular" + apiKey + languageEN)
    Call<MovieResponseList> getPopularMovies();

    @GET("search/movie" + apiKey + languageEN)
    Call<MovieResponseList> searchMovieByTitle(@Query("query") String title);


}
