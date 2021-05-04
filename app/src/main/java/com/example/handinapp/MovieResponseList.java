package com.example.handinapp;

import java.util.ArrayList;
import java.util.List;

public class MovieResponseList {
    List<MovieResponse> results;

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> result = new ArrayList<>();
        for (MovieResponse mr: results) {
            result.add(mr.getMovie());
        }
        return result;
    }
}
