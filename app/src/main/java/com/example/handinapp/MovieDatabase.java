package com.example.handinapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MovieDBItem.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();

    private static MovieDatabase instance;

    public static synchronized MovieDatabase getInstance(Context context)
    {
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
