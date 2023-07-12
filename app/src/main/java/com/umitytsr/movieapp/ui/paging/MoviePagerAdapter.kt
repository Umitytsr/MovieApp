package com.umitytsr.movieapp.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.databinding.ItemRowFavoriteBinding
import com.umitytsr.movieapp.databinding.ItemRowMovieBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.util.Constants

class MoviePagerAdapter() :
    PagingDataAdapter<ResultMovie, MoviePagerAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {
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
        fun bind(movie: ResultMovie) {
            Glide.with(binding.root).load(Constants.IMAGE_URL.plus(movie.posterPath))
                .into(binding.imageView)
            with(binding) {
                voteAverageTV.text = movie.voteAverage?.format(1)
            }
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<ResultMovie>() {
            override fun areItemsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
                // Id is unique.
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ResultMovie, newItem: ResultMovie): Boolean {
                return oldItem == newItem
            }
        }
    }
}


