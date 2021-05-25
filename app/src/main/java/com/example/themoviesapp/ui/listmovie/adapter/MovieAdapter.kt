package com.example.themoviesapp.ui.listmovie.adapter

import android.R.attr.data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Movie
import com.example.themoviesapp.databinding.ListMovieItemBinding


/**
 * Created on : 25/05/21 | 13.53
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    val listData = ArrayList<Movie>()
    var onLoadMore: (() -> Unit)? = null



    fun setData(listMovie: List<Movie>){
        if (listMovie == null) return
        listData.clear()
        listData.addAll(listMovie)
        notifyDataSetChanged()
    }

    fun updateData(listMovie: List<Movie>){

        val insertIndex = listData.size - 1
        listData.addAll(insertIndex, listMovie)
        notifyItemRangeInserted(insertIndex, listMovie.size)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MovieViewHolder =
        MovieViewHolder(
            ListMovieItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val currentData = listData[position]
        holder.bind(currentData)
        if ((position >= getItemCount() - 1))
            onLoadMore?.invoke()
    }

    override fun getItemCount() = listData.size

    inner class MovieViewHolder(val binding: ListMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.movie = data
        }
    }



}