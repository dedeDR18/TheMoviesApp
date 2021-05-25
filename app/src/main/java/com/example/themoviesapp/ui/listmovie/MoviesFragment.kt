package com.example.themoviesapp.ui.listmovie

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.domain.model.Genre
import com.example.themoviesapp.R
import com.example.themoviesapp.databinding.FragmentMoviesBinding
import com.example.themoviesapp.ui.listmovie.adapter.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private var genre: Genre? = null
    private val moviesViewModel: MoviesViewModel by viewModel()
    lateinit var movieAdapter: MovieAdapter
    private lateinit var navController:NavController
    private var mLoading = true
    private var totalPages = 0
    private var pageToLoad = 1
    private var currentPageLoaded = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            genre = bundle.getParcelable("genre")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()

        navController = Navigation.findNavController(view)

        genre?.let { genre ->
            fetchData(genre.id, 1)
        }

        observe()
        handleLoadMore()
        handleOnClick()
    }

    private fun observe() {
        genre?.let { genre ->
            moviesViewModel.lastestPageExecute(genre.id)
                .observe(viewLifecycleOwner, Observer { paging ->
                    paging?.let {
                        pageToLoad = it.currentPage + 1
                        totalPages = it.totalPage
                        currentPageLoaded = it.currentPage
                    }
                })
        }
    }

    private fun fetchData(genreId: Int, page: Int) {
        binding.loading = mLoading
        moviesViewModel.fetchMoviesByGenre(genreId, page)
            .observe(viewLifecycleOwner, Observer { data ->
                data?.let { result ->
                    when (result) {
                        is Resource.Loading -> {
                            mLoading = true
                            binding.loading = mLoading
                        }

                        is Resource.Success -> {
                            mLoading = false
                            binding.loading = mLoading
                            if (page == 1) movieAdapter.setData(result.data!!) else movieAdapter.updateData(
                                result.data!!
                            )
                        }
                        is Resource.Error -> {
                            mLoading = false
                            binding.loading = mLoading
                            Toast.makeText(requireContext(), "error..", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
    }

    private fun initRv() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
    }

    private fun handleLoadMore() {
        movieAdapter.onLoadMore = {
            Log.d("TAG", "Load More...")
            if (currentPageLoaded + 1 <= totalPages) {
                genre?.let { genre ->
                    fetchData(genre.id, pageToLoad)
                    Toast.makeText(
                        requireContext(),
                        "total data = ${movieAdapter.itemCount}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun handleOnClick(){
        movieAdapter.onClickItem = { movie ->
            val bundle = bundleOf(
                "movie" to movie
            )
            navController.navigate(R.id.action_moviesFragment_to_detailMovieFragment, bundle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}