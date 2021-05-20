package com.example.handinapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;

    public MovieViewModel(Application app)
    {
        super(app);
        repository = MovieRepository.getInstance(app);
    }

    public LiveData<ArrayList<Movie>> getMoviesList()
    {
        return repository.getMoviesList();
    }

    public void searchPopularMovies()
    {
        repository.searchPopularMovies();
    }

    public void searchMovieByTitle(String title)
    {
        repository.searchMovieByTitle(title);
    }

    public void searchMovieById(int id)
    {
        repository.searchMovieById(id);
    }

    public LiveData<MovieResponse> getMovie()
    {
        return repository.getMovieResponse();
    }

    public void saveMovieLocally(MovieDBItem movieDBItem)
    {
        repository.saveMovieLocally(movieDBItem);
    }

    public void deleteMovieLocally(MovieDBItem movieDBItem) {
        repository.deleteMovieLocally(movieDBItem);
    }

    public LiveData<MovieDBItem> getMovieLocally(int id) {
        return repository.getMovieLocally(id);
    }

//    public LiveData<List<Movie>> getWatchedList()
//    {
//        return repository.getWatchedList();
//    }

//    public LiveData<List<Movie>> getWatchLaterList()
//    {
//        return repository.getWatchLaterList();
//    }
}
