package com.example.themoviesapp.ui.review

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.themoviesapp.databinding.FragmentReviewBinding
import com.example.themoviesapp.ui.review.adapter.ReviewAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    lateinit var reviewAdapter: ReviewAdapter
    private val reviewViewModel: ReviewViewModel by viewModel()
    private var mLoading = true
    private var totalPages = 0
    private var pageToLoad = 1
    private var currentPageLoaded = 0

    var movie: Movie? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            movie = bundle.getParcelable("movie")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loading = true
        Handler(Looper.getMainLooper()).postDelayed({
            initRv()
            movie?.let { movie ->
                fetchData(movie.id, 1)
            }
            observe()
            handleLoadMore()
        }, 500)

    }

    private fun observe() {
        movie?.let { movie ->
            reviewViewModel.lastestPageExecute(movie.id)
                .observe(viewLifecycleOwner, Observer { paging ->
                    paging?.let {
                        pageToLoad = it.currentPage + 1
                        totalPages = it.totalPage
                        currentPageLoaded = it.currentPage
                    }
                })
        }

    }


    private fun initRv() {
        reviewAdapter = ReviewAdapter()
        binding.rvReview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val divider = DividerItemDecoration(
                requireContext(),
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(divider)
            adapter = reviewAdapter
        }
    }

    private fun fetchData(movieId: Int, page: Int) {
        reviewViewModel.fetchReview(movieId, page)
            .observe(viewLifecycleOwner, Observer { data ->
                data?.let { result ->
                    when (result) {
                        is Resource.Loading -> binding.loading = true
                        is Resource.Success -> {
                            binding.loading = false
                            result.data?.let { list ->
                                if (page == 1) reviewAdapter.setData(list) else reviewAdapter.updateData(
                                    list
                                )
                            }
                        }
                        is Resource.Error -> {
                            binding.loading = false
                            Log.d("TAG", "Error...")
                        }
                    }
                }

            })

    }

    private fun handleLoadMore() {
        reviewAdapter.onLoadMore = {
            Log.d("TAG", "Loading review")
            if (currentPageLoaded + 1 <= totalPages) {
                movie?.let { movie ->
                    fetchData(movie.id, pageToLoad)
                    Toast.makeText(
                        requireContext(),
                        "total data = ${reviewAdapter.itemCount}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}