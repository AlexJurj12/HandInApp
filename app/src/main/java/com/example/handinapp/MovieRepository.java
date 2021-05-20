package com.example.handinapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.AEADBadTagException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MovieRepository {

    private MovieAPI movieAPI;

    private static MovieRepository instance;
    private MutableLiveData<ArrayList<Movie>> movies;
    private MutableLiveData<MovieResponse> movieResponse;
    private LiveData<MovieDBItem> savedMovie;
    private final ExecutorService executorService;
    private final MovieDAO movieDAO;

    private MovieRepository(Application app)
    {
        movieAPI = ServiceGenerator.getMovieAPI();
        movies = new MutableLiveData<>();
        movieResponse = new MutableLiveData<>();
        executorService = Executors.newFixedThreadPool(2);
        MovieDatabase database = MovieDatabase.getInstance(app);
        movieDAO = database.movieDAO();
    }

    public static synchronized MovieRepository getInstance(Application app) {
        if (instance == null) {
            instance = new MovieRepository(app);
        }
        return instance;
    }

    public LiveData<ArrayList<Movie>> getMoviesList()
    {
        return movies;
    }

    public MutableLiveData<MovieResponse> getMovieResponse() {
        return movieResponse;
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
                if (response.isSuccessful() && response.body().getMovies() != null) {
                    movies.setValue(response.body().getMovies());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MovieResponseList> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });
    }

    public void searchMovieById(int id) {
        Call<MovieResponse> call = movieAPI.searchMovieById(id);
        call.enqueue(new Callback<MovieResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieResponse.setValue(response.body());
                }
                else
                {
                    System.out.println("body is null");
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });
    }

    public void saveMovieLocally(MovieDBItem movieDBItem) {
        executorService.execute(()->movieDAO.saveMovie(movieDBItem));
    }

    public void deleteMovieLocally(MovieDBItem movieDBItem) {
        executorService.execute(()->movieDAO.saveMovie(movieDBItem));
    }

    public LiveData<MovieDBItem> getMovieLocally(int id)
    {
        executorService.execute(()->movieDAO.getMovie(id));
        return null;
    }

//    public LiveData<List<Movie>> getWatchedList() {
//        List<MovieDBItem> list = watchedList.getValue();
//        ArrayList<MovieDBItem> arrayList = new ArrayList<>(list);
//        ArrayList<Movie> temp = new ArrayList<>();
//        for (int i = 0; i< list.size(); i++) {
//            Movie m = new Movie()
//        }
//        return watchedList;
//    }
//
//    public LiveData<List<Movie>> getWatchLaterList() {
//        return watchLaterList;
//    }
}
