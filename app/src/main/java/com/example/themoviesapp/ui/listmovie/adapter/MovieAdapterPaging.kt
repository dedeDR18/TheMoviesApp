package com.example.themoviesapp.ui.listmovie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.source.local.entity.MovieEntity
import com.example.themoviesapp.databinding.ListMovieItemBinding

/**
 * Created on : 24/05/21 | 20.00
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class MovieAdapterPaging : PagingDataAdapter<MovieEntity, MovieAdapterPaging.MovieViewHolder>(MOVIE_COMPARATOR){

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val curretItem = getItem(position)
        if (curretItem != null) {
            holder.bind(curretItem)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }


    class MovieViewHolder(private val binding: ListMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieEntity){

        }

    }







}