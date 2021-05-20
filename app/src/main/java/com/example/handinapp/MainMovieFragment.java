package com.example.handinapp;

import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainMovieFragment extends Fragment {

    RecyclerView itemList;
    MovieAdapter movieAdapter;
    MovieViewModel movieViewModel;

    public MainMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_movie, container, false);

        //initialize recyclerview
        itemList = view.findViewById(R.id.rv);
        itemList.hasFixedSize();
        itemList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ArrayList<Movie> movies = new ArrayList<>();

        //get popular movies and put them in the recyclerview
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieAdapter = new MovieAdapter(movies, clickedMovieIndex ->{
            MainMovieFragmentDirections.ActionMainMovieFragmentToMovieDetailFragment actionMainActivityToMovieDetailFragment =
                    MainMovieFragmentDirections.actionMainMovieFragmentToMovieDetailFragment(clickedMovieIndex);
            Navigation.findNavController(view).navigate(actionMainActivityToMovieDetailFragment);
        });
        itemList.setAdapter(movieAdapter);
        movieViewModel.getMoviesList().observe(getViewLifecycleOwner(), moviesList -> {
            movieAdapter.updateData(moviesList);
        });

        movieViewModel.searchPopularMovies();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!query.isEmpty())
                {
                    movieViewModel.searchMovieByTitle(query);
                }else
                {
                    movieViewModel.searchPopularMovies();

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!newText.isEmpty())
                {
                    movieViewModel.searchMovieByTitle(newText);
                }else
                {
                    movieViewModel.searchPopularMovies();

                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, menuInflater);
    }

}