package com.umitytsr.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.movieapp.databinding.ItemRowMovieBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.Extensions.loadImage
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.util.Constants

class MovieAdapter(private val movies: List<Movie>,private val movieItemClickListener: MovieItemClickListener)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){


    inner class MovieViewHolder(private val binding: ItemRowMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            binding.imageView.loadImage(Constants.IMAGE_URL.plus(movie.posterPath))
            with(binding) {
                voteAverageTV.text = movie.voteAverage?.format(1)
                cardView.setOnClickListener {
                    movieItemClickListener.movieItemClicked(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowMovieBinding.inflate(layoutInflater,parent,false)

        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    interface MovieItemClickListener{
        fun movieItemClicked(movie: Movie)
    }
}