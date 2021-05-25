package com.example.core.utils

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on : 25/05/21 | 16.01
 * Author     : dededarirahmadi
 * Name       : dededarirahmadi
 * Email      : dededarirahmadi@gmail.com
 */

abstract class EndlessRecyclerviewScrollListener(layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener(){

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 5
    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to load.
    private var loading = false
    // Sets the starting page index
    private val startingPageIndex = 1
    // The current offset index of data you have loaded
    private var currentPage = startingPageIndex

    private val mLayoutManager: RecyclerView.LayoutManager = layoutManager

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
    {
        val lastVisibleItemPosition: Int = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        val totalItemCount = mLayoutManager.itemCount
        Log.d("TAG", "::onScrolled: lastVisibleItemPosition = $lastVisibleItemPosition")
        Log.d("TAG", "::onScrolled: previousTotalItemCount = $previousTotalItemCount")
        Log.d("TAG", "::onScrolled: totalItemCount = $totalItemCount")
        Log.d("TAG", "::onScrolled: visibleThreshold = $visibleThreshold")
        Log.d("TAG", "::onScrolled: currentPage = $currentPage")
        Log.d("TAG", "::onScrolled: loading = $loading")

        /**
         * If the total item count is zero and the previous isn't, assume the
         * list is invalidated and should be reset back to initial state
         *
         * If it’s still loading, we check to see if the dataset count has
         * changed, if so we conclude it has finished loading and update the current page
         * number and total item count.
         *
         * If it isn’t currently loading, we check to see if we have breached
         * the visibleThreshold and need to reload more data.
         * If we do need to reload some more data, we execute onLoadMore to fetch the data.
         * threshold should reflect how many total columns there are too
         *
         * If the total item count is zero and the previous isn't, assume the
         * list is invalidated and should be reset back to initial state
         */
        if (totalItemCount < previousTotalItemCount)
        {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0)
            {
                this.loading = true
            }
        }

        /**
         * If it’s still loading, we check to see if the dataset count has
         * changed, if so we conclude it has finished loading and update the current page
         * number and total item count.
         */
        if (loading && totalItemCount > previousTotalItemCount)
        {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        /**
         * If it isn’t currently loading, we check to see if we have breached
         * the visibleThreshold and need to reload more data.
         * If we do need to reload some more data, we execute onLoadMore to fetch the data.
         */
        if (!loading && (lastVisibleItemPosition + visibleThreshold > totalItemCount))
        {
            Log.d("TAG", "::onScrolled: (!loading && (lastVisibleItemPosition + visibleThreshold > totalItemCount)) = true")
            currentPage++
            onLoadMore(currentPage, recyclerView)
            loading = true
        }
    }

    // Call this method whenever performing new searches
    fun resetState()
    {

        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore(page: Int, recyclerView: RecyclerView)

}