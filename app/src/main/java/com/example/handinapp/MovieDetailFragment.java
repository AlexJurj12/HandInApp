package com.example.handinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

public class MovieDetailFragment extends Fragment {

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView runtimeTextView;
    private TextView genresTextView;
    private TextView overviewTextView;
    private Spinner spinner;

    Movie savedMovie;
    MovieDBItem movieDBItem;
    MovieViewModel movieViewModel;


    public MovieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        posterImageView = view.findViewById(R.id.posterImageView);
        titleTextView = view.findViewById(R.id.titleTextView);
        releaseDateTextView = view.findViewById(R.id.releaseDateTextView);
        runtimeTextView = view.findViewById(R.id.runtimeTextView);
        genresTextView = view.findViewById(R.id.genresTextView);
        overviewTextView = view.findViewById(R.id.overviewTextView);
        spinner = view.findViewById(R.id.dropdown);

        int id = MovieDetailFragmentArgs.fromBundle(getArguments()).getMovieId();
        movieViewModel.searchMovieById(id);
        movieViewModel.getMovie().observe(getViewLifecycleOwner(), this::setMovieDetails);

        initSpinner();

        return view;
    }

    public void initSpinner()
    {
        String[] choices = getResources().getStringArray(R.array.movieWatchChoice);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, choices);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                if (!text.equals("None"))
                {
                    movieDBItem = new MovieDBItem(savedMovie.getId(), savedMovie.getTitle(), savedMovie.getPoster(),text);
                    movieViewModel.saveMovieLocally(movieDBItem);
                }
//                else
//                {
//                    movieDBItem = new MovieDBItem(savedMovie.getId(), savedMovie.getTitle(), savedMovie.getPoster(),text);
//                    movieViewModel.deleteMovieLocally(movieDBItem);
//                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void setMovieDetails(MovieResponse movieResponse)
    {
        System.out.println(movieResponse.toString());
        savedMovie = movieResponse.getMovie();
        titleTextView.setText(movieResponse.getTitle());

        releaseDateTextView.setText(R.string.release_date);
        releaseDateTextView.append(movieResponse.getRelease_date());

        runtimeTextView.setText(R.string.duration);
        runtimeTextView.append(String.valueOf(movieResponse.getRuntime()));
        runtimeTextView.append(" minutes");

        genresTextView.setText(R.string.genres);
        for (int i = 0; i < movieResponse.getGenres().size() - 1; i++) {
            String genre = movieResponse.getGenres().get(i).getName();
            System.out.println(genre);
            genresTextView.append(genre);
            genresTextView.append(", ");
        }
        int lastItem = movieResponse.getGenres().size() - 1;
        String genre = movieResponse.getGenres().get(lastItem).getName();
        genresTextView.append(genre);

        overviewTextView.setText(R.string.overview);
        overviewTextView.append(movieResponse.getOverview());

        movieResponse.setPosterWithDummy();
        Glide.with(this).load(movieResponse.getPoster_path())
                .placeholder(R.drawable.unnamed).error(R.drawable.unnamed).into(posterImageView);
    }
}