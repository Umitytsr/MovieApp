package com.umitytsr.movieapp.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.movieapp.databinding.ItemRowFavoriteBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.Extensions.loadImage
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.util.Constants

class MoviePagerAdapter(private val moviePagerItemClickListener: MoviePagerItemClickListener):
    PagingDataAdapter<Movie, MoviePagerAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowFavoriteBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    inner class MovieViewHolder(private val binding: ItemRowFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.imageView.loadImage(Constants.IMAGE_URL.plus(movie.posterPath))
            with(binding) {
                voteAverageTV.text = movie.voteAverage?.format(1)
                cardViewFavorite.setOnClickListener {
                    moviePagerItemClickListener.movieItemClicked(movie,adapterPosition)
                }
            }
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                // Id is unique.
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface MoviePagerItemClickListener{
        fun movieItemClicked(movie: Movie,position: Int)
    }
}


