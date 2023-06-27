package com.umitytsr.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.databinding.ItemRowMovieBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.util.Constants

class MovieAdapter(private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    inner class MovieViewHolder(private val binding: ItemRowMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            Glide.with(binding.root).load(Constants.IMAGE_URL.plus(movie.posterPath)).into(binding.imageView)
            binding.voteAverageTV.text = movie.voteAverage.format(1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowMovieBinding.inflate(layoutInflater)

        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}