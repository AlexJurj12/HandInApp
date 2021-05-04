package com.example.handinapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {
    private MovieRepository repository;

    public MovieViewModel()
    {
        repository = MovieRepository.getInstance();
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
}
