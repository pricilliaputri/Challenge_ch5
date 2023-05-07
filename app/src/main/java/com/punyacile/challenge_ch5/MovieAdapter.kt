package com.punyacile.challenge_ch5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.punyacile.challenge_ch5.databinding.MovieListBinding
import com.punyacile.challenge_ch5.model.Result

class MovieAdapter(var listMovie : List<Result>,
                   var onItemClick: ((Result) -> Unit)): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(var binding: MovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = MovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.binding.titleMovie.text = listMovie[position].originalTitle
        holder.binding.releaseMovie.text = listMovie[position].releaseDate
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500${listMovie[position].posterPath}")
            .into(holder.binding.imgMovie)
        holder.binding.cardView.setOnClickListener {
            onItemClick(listMovie[position])
        }
    }

        override fun getItemCount(): Int {
            return listMovie.size

    }
}