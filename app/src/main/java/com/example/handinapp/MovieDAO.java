package com.example.handinapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDAO {

    @Query("Select * From movies Where type=(:watched)")
    LiveData<List<MovieDBItem>> getWatchedList(String watched);
    @Query("Select * From movies Where type=(:watchLater)")
    LiveData<List<MovieDBItem>> getWatchLaterList(String watchLater);
    @Insert
    void saveMovie(MovieDBItem movieDBItem);
    @Update
    void updateMovie(MovieDBItem movieDBItem);
    @Delete
    void deleteMovie(MovieDBItem movieDBItem);
    @Query("Select * from movies where id=(:id)")
    LiveData<MovieDBItem> getMovie(int id);
}
