package com.example.themoviesapp.ui.detailmovie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.themoviesapp.R
import com.example.themoviesapp.databinding.FragmentDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModel()
    lateinit var navController: NavController

    var movie: Movie? = null
    private var mLoading = true
    private var totalPages = 0
    private var pageToLoad = 1
    private var currentPageLoaded = 0
    private var totalResult = 0


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
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.movie = movie

        movie?.let { movie ->
            fetchData(movie.id, 1)
        }
        observe()
        handleOnClickReview()
    }

    private fun observe() {
        movie?.let { movie ->
            detailViewModel.lastestPageExecute(movie.id)
                .observe(viewLifecycleOwner, Observer { paging ->
                    paging?.let {
                        pageToLoad = it.currentPage + 1
                        totalPages = it.totalPage
                        currentPageLoaded = it.currentPage
                        totalResult = it.totalResult
                        binding.totalReview = totalResult
                    }
                })
        }

    }

    private fun fetchData(movieId: Int, page: Int) {
        detailViewModel.fetchReview(movieId, page)
            .observe(viewLifecycleOwner, Observer { data ->
                data?.let { result ->
                    when (result) {
                        is Resource.Loading -> {
                            Log.d("TAG", "Loading review")
                        }
                        is Resource.Success -> {
                            Log.d("TAG", "data ada")
                        }
                        is Resource.Error -> {
                            Log.d("TAG", "Error...")
                        }
                    }
                }

            })

    }

    private fun handleOnClickReview(){
        binding.tvSeeAllReview.setOnClickListener {
            if (totalResult == 0){
                Toast.makeText(requireContext(), "0 Review", Toast.LENGTH_SHORT).show()
            } else {
                goToReview()
            }
        }
    }

    private fun goToReview(){
        movie?.let { movie ->
            val bundle = bundleOf(
                "movie" to movie
            )
            navController.navigate(R.id.action_detailMovieFragment_to_reviewFragment, bundle)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}