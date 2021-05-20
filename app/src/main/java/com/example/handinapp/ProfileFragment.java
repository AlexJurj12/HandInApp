package com.example.handinapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    RecyclerView watchedList;
    RecyclerView watchLaterList;
    TextView name;
    ImageView profileImage;
    MovieViewModel movieViewModel;
    MovieAdapter movieAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        name = view.findViewById(R.id.profileUsername);
        profileImage = view.findViewById(R.id.profilePicture);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(view.getContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            Uri personPhoto = acct.getPhotoUrl();
            name.setText(personName);
            Glide.with(view.getContext()).load(personPhoto)
                    .placeholder(R.drawable.unnamed).error(R.drawable.unnamed).into(profileImage);

        }


        //Did not manage to get working
//        watchedList = view.findViewById(R.id.rvWatched);
//        watchedList.hasFixedSize();
//        watchedList.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        ArrayList<Movie> watched = new ArrayList<>();
//        movieAdapter = new MovieAdapter(watched, clickedMovieIndex ->{
//            ProfileFragmentDirections.ActionProfileFragmentToMovieDetailFragment actionProfileFragmentToMovieDetailFragment =
//                    ProfileFragmentDirections.actionProfileFragmentToMovieDetailFragment(clickedMovieIndex);
//            Navigation.findNavController(view).navigate(actionProfileFragmentToMovieDetailFragment);
//        });
//        watchedList.setAdapter(movieAdapter);
//        movieViewModel.getWatchedList().observe(getViewLifecycleOwner(), moviesList -> {
//            movieAdapter.updateData(moviesList);
//        });
//
//        watchLaterList = view.findViewById(R.id.rvWatchLater);
//        watchLaterList.hasFixedSize();
//        watchLaterList.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        ArrayList<Movie> watchLater = new ArrayList<>();
//        movieAdapter = new MovieAdapter(watchLater, clickedMovieIndex ->{
//            ProfileFragmentDirections.ActionProfileFragmentToMovieDetailFragment actionProfileFragmentToMovieDetailFragment =
//                    ProfileFragmentDirections.actionProfileFragmentToMovieDetailFragment(clickedMovieIndex);
//            Navigation.findNavController(view).navigate(actionProfileFragmentToMovieDetailFragment);
//        });
//        watchLaterList.setAdapter(movieAdapter);
//        movieViewModel.getMoviesList().observe(getViewLifecycleOwner(), moviesList -> {
//            movieAdapter.updateData(moviesList);
//        });


        return view;
    }
}