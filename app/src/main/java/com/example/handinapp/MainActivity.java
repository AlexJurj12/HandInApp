package com.example.handinapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnListItemClickListener{

    RecyclerView itemList;
    MovieAdapter movieAdapter;
    MovieViewModel movieViewModel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize recyclerview
        itemList = findViewById(R.id.rv);
        itemList.hasFixedSize();
        itemList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Movie> movies = new ArrayList<>();

        //get popular movies and put them in the recyclerview
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieAdapter = new MovieAdapter(movies, this);
        itemList.setAdapter(movieAdapter);
        movieViewModel.getMoviesList().observe(this, moviesList -> {
            movieAdapter.updateData(moviesList);
        });

        movieViewModel.searchPopularMovies();
        //movieViewModel.searchMovieByTitle("avenger");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.action_search:
//                System.out.println("merge searchu");
//                return true;
//            default: return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        int movieNumber = clickedItemIndex + 1;
        Toast.makeText(this, "Movie Number: " + movieNumber, Toast.LENGTH_SHORT).show();
//        Context context = getApplicationContext();
//        Class destination = SecondActivity.class;
//        Intent intent = new Intent(MainActivity.this, PlantDetails.class);
//        startActivity(intent);

    }
}