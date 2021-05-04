package com.example.handinapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import javax.crypto.AEADBadTagException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MovieRepository {

    private MovieAPI movieAPI;

    private static MovieRepository instance;
    private MutableLiveData<ArrayList<Movie>> movies;

    private MovieRepository()
    {
        movieAPI = ServiceGenerator.getMovieAPI();
        movies = new MutableLiveData<>();
    }

    public static synchronized MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    public LiveData<ArrayList<Movie>> getMoviesList()
    {
        return movies;
    }

    public void searchPopularMovies()
    {
        Call<MovieResponseList> call = movieAPI.getPopularMovies();
        call.enqueue(new Callback<MovieResponseList>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MovieResponseList> call, Response<MovieResponseList> response) {
                if (response.isSuccessful()) {
                    movies.setValue(response.body().getMovies());
                    System.out.println(movies.getValue().get(0).toStringCustom());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MovieResponseList> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });
    }

    public void searchMovieByTitle(String title)
    {
        Call<MovieResponseList> call = movieAPI.searchMovieByTitle(title);
        call.enqueue(new Callback<MovieResponseList>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MovieResponseList> call, Response<MovieResponseList> response) {
                if (response.isSuccessful()) {
                    movies.setValue(response.body().getMovies());
                    System.out.println(movies.getValue().get(0).toStringCustom());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MovieResponseList> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });
    }

//    public Movie searchMovieDetails(int id)
//    {
//        Call<Movie> call = movieAPI.getMovieDetail(id);
//        call.enqueue(new Callback<Movie>() {
//            @EverythingIsNonNull
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                if (response.isSuccessful()) {
//                    mv = response.body();
//                }
//            }
//            @EverythingIsNonNull
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//                Log.i("Retrofit", "Something went wrong :(");
//            }
//        });
//    }

//    public void searchForMovie(String movieName) {
//        Call<MovieResponse> call = movieAPI.searchMovie(pokemonName);
//        call.enqueue(new Callback<MovieResponse>() {
//            @EverythingIsNonNull
//            @Override
//            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
//                if (response.isSuccessful()) {
//                    searchedPokemon.setValue(response.body().getPokemon());
//                }
//            }
//            @EverythingIsNonNull
//            @Override
//            public void onFailure(Call<PokemonResponse> call, Throwable t) {
//                Log.i("Retrofit", "Something went wrong :(");
//            }
//        });
//    }
}
