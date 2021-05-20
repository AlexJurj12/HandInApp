package com.example.handinapp;

public class Movie {
    private int id;
    private String title;
    private String poster = "https://image.tmdb.org/t/p/w342";

    Movie(int id,String title, String posterPath)
    {
        this.id = id;
        this.title = title;
        if (posterPath == null)
        {
            poster = "NA";
        }
        else
        {
            poster += posterPath;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public String getPoster()
    {
        return poster;
    }

    public String toStringCustom()
    {
        return "title: " + title + " / " + "   " + id;
    }
}
