package moe.arata210.starcinema.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import moe.arata210.starcinema.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieD.setText("导演: " + movie.getDirector());
        holder.movieM.setText("主演: " + movie.getMainActors());
        holder.movieRating.setText(movie.getRating() + "分");
        holder.moviePoster.setImageResource(movie.getPoster());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle, movieD, movieM, movieRating;
        ImageView moviePoster;
        Button buyTicketButton;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieD = itemView.findViewById(R.id.movieD);
            movieM = itemView.findViewById(R.id.movieM);
            movieRating = itemView.findViewById(R.id.movieRating);
            moviePoster = itemView.findViewById(R.id.moviePoster);
            buyTicketButton = itemView.findViewById(R.id.buyTicketButton);
        }
    }
}
