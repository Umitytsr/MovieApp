package com.umitytsr.movieapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.databinding.ItemRowMovieBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.model.Actor
import com.umitytsr.movieapp.util.Constants

class ActorAdapter(private val actor : List<Actor>)
    :RecyclerView.Adapter<ActorAdapter.ActorViewHolder>(){

        inner class ActorViewHolder(private val binding: ItemRowMovieBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind(actor: Actor){
                Glide.with(binding.root).load(Constants.IMAGE_URL.plus(actor.posterPath)).into(binding.imageView)
                binding.voteAverageTV.text = actor.voteAverage?.format(1)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowMovieBinding.inflate(layoutInflater,parent,false)

        return ActorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return actor.size
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actor[position])
    }
}