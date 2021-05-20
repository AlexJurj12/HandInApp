package com.example.handinapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private String dummyPoster = "https://image.tmdb.org/t/p/w780";
    private String release_date;
    private int runtime;
    private List<Genres> genres = new ArrayList<>();

    public MovieResponse()
    {

    }

    public Movie getMovie()
    {
        return new Movie(id,title,poster_path);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = dummyPoster + poster_path;
    }

    public void setPosterWithDummy()
    {
        dummyPoster += poster_path;
        poster_path = dummyPoster;
        dummyPoster = "https://image.tmdb.org/t/p/w780";
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", dummyPoster='" + dummyPoster + '\'' +
                ", release_date='" + release_date + '\'' +
                ", runtime=" + runtime +
                ", genres=" + genres +
                '}';
    }

    public class Genres {
        int id;
        String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Genres{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
