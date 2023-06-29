package com.umitytsr.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.databinding.ItemRowMovieBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.model.TvSeries
import com.umitytsr.movieapp.ui.home.HomeFragment
import com.umitytsr.movieapp.util.Constants

class TvSeriesAdapter(private val tvSeries: List<TvSeries>,tvSeriesItemClickListener: HomeFragment)
    :RecyclerView.Adapter<TvSeriesAdapter.TvSeriesViewHolder>(){

    private val tvSeriesItemClickListener : TvSeriesItemClickListener


    init{
        this.tvSeriesItemClickListener = tvSeriesItemClickListener
    }

    inner class TvSeriesViewHolder(private val binding: ItemRowMovieBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(tvSeries: TvSeries){
                Glide.with(binding.root).load(Constants.IMAGE_URL.plus(tvSeries.posterPath)).into(binding.imageView)
                binding.voteAverageTV.text = tvSeries.voteAverage?.format(1)
                with(binding){
                    voteAverageTV.text = tvSeries.voteAverage?.format(1)
                    cardView.setOnClickListener {
                        tvSeriesItemClickListener.tvSeriesItemClicked(tvSeries)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowMovieBinding.inflate(layoutInflater,parent,false)

        return TvSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tvSeries.size
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        holder.bind(tvSeries[position])
    }

    interface TvSeriesItemClickListener{
        fun tvSeriesItemClicked(tvSeries: TvSeries)
    }
}