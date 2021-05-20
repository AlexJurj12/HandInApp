package com.example.handinapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movieArrayList;
    final private OnListItemClickListener onListItemClickListener;

    public MovieAdapter(ArrayList<Movie> movies, OnListItemClickListener onListItemClickListener)
    {
        movieArrayList = movies;
        this.onListItemClickListener = onListItemClickListener;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.name.setText(movieArrayList.get(position).getTitle());
        Glide.with(viewHolder.itemView).load(movieArrayList.get(position).getPoster())
                .placeholder(R.drawable.unnamed).error(R.drawable.unnamed).into(viewHolder.icon);
        viewHolder.itemContainer.setOnClickListener(v -> onListItemClickListener.onListItemClick(movieArrayList.get(position).getId()));
    }

    public int getItemCount() {
        return movieArrayList.size();
    }

    public void updateData(ArrayList<Movie> popularMovies) {
        movieArrayList = popularMovies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView icon;
        LinearLayout itemContainer;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            icon = itemView.findViewById(R.id.iv_icon);
            itemContainer = itemView.findViewById(R.id.itemContainer);
        }

    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

}
