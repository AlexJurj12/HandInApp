package com.example.handinapp;

public class Movie {
    private int id;
    private String title;
    private String release_date;
    private String poster = "https://image.tmdb.org/t/p/w342";

    Movie(String title, String release_date, String posterPath)
    {
        this.title = title;
        this.release_date = release_date;
        if (posterPath == null)
        {
            poster = "NA";
        }
        else
        {
            poster += posterPath;
        }
    }

    public String getTitle()
    {
        return title;
    }

    public String getReleaseDate()
    {
        return release_date;
    }

    public String getPoster()
    {
        return poster;
    }

    public String toStringCustom()
    {
        return "11111111111111111111111111 title: " + title + " / " + release_date;
    }
}
