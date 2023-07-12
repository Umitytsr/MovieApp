package com.umitytsr.movieapp.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.data.model.series.ResultTvSeries
import com.umitytsr.movieapp.databinding.ItemRowFavoriteBinding
import com.umitytsr.movieapp.databinding.ItemRowMovieBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.util.Constants

class TvSeriesPagerAdapter:
    PagingDataAdapter<ResultTvSeries, TvSeriesPagerAdapter.TvSeriesViewHolder>(MOVIE_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowFavoriteBinding.inflate(layoutInflater, parent, false)
        return TvSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            holder.bind(movie)
        }
    }

    inner class TvSeriesViewHolder(private val binding: ItemRowFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvSeries: ResultTvSeries) {
            Glide.with(binding.root).load(Constants.IMAGE_URL.plus(tvSeries.posterPath))
                .into(binding.imageView)
            with(binding) {
                voteAverageTV.text = tvSeries.voteAverage?.format(1)
            }
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<ResultTvSeries>() {
            override fun areItemsTheSame(oldItem: ResultTvSeries, newItem: ResultTvSeries): Boolean {
                // Id is unique.
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: ResultTvSeries, newItem: ResultTvSeries): Boolean {
                return oldItem == newItem
            }
        }
    }
}