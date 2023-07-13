package com.umitytsr.movieapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.databinding.ItemRowFavoriteBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.util.Constants

class FavoriteAdapter(
    private val favorite: List<Favorite>,
    private val favoriteItemClickListener: FavoriteItemClickListener
    ) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>(){

    inner class FavoriteViewHolder(private val binding: ItemRowFavoriteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(favorite:Favorite){
            Glide.with(binding.root).load(Constants.IMAGE_URL.plus(favorite.posterPath)).into(binding.imageView)
            with(binding){
                voteAverageTV.text = favorite.voteAverage?.format(1)
                cardViewFavorite.setOnClickListener {
                    favoriteItemClickListener.favoriteItemClicked(favorite)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowFavoriteBinding.inflate(layoutInflater,parent,false)

        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorite[position])
    }

    interface FavoriteItemClickListener{
        fun favoriteItemClicked(favorite: Favorite)
    }
}