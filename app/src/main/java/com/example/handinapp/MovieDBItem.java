package com.example.handinapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieDBItem {
    @PrimaryKey
    private int id;
    private String title;
    private String poster;
    private String type;

    public MovieDBItem(int id, String title, String poster, String type) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.type = type;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
