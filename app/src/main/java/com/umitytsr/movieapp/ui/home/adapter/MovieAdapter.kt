package com.umitytsr.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.databinding.ItemRowMovieBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.ui.home.HomeFragment
import com.umitytsr.movieapp.util.Constants

class MovieAdapter(private val movies: List<Movie>, movieItemClickListener: HomeFragment)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private val movieItemClickListener : MovieItemClickListener

    init {
        this.movieItemClickListener = movieItemClickListener
    }

    inner class MovieViewHolder(private val binding: ItemRowMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie) {
            Glide.with(binding.root).load(Constants.IMAGE_URL.plus(movie.posterPath)).into(binding.imageView)
            binding.voteAverageTV.text = movie.voteAverage?.format(1)
            binding.cardView.setOnClickListener {
                movieItemClickListener.movieIntemClicked(movie)
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
        fun movieIntemClicked(movie: Movie)
    }
}