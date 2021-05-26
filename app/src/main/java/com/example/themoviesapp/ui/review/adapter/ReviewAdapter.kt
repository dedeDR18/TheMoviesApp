package com.example.themoviesapp.ui.review.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Review
import com.example.themoviesapp.databinding.ItemListReviewBinding

/**
 * Created on : 26/05/21 | 12.54
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){

    var listData = ArrayList<Review>()
    var onLoadMore: (() -> Unit)? = null

    fun setData(listReview: List<Review>) {
        if (listReview == null) return
        listData.clear()
        listData.addAll(listReview)
        notifyDataSetChanged()
    }

    fun updateData(listReview: List<Review>) {
        val insertIndex = listData.size - 1
        listData.addAll(insertIndex, listReview)
        notifyItemRangeInserted(insertIndex, listReview.size)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReviewViewHolder = ReviewViewHolder(ItemListReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val currentData = listData[position]
        holder.bind(currentData)
        if ((position >= getItemCount() - 1))
            onLoadMore?.invoke()
    }

    override fun getItemCount() = listData.size

    class ReviewViewHolder(val binding: ItemListReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Review){
            binding.review = data
        }
    }
}