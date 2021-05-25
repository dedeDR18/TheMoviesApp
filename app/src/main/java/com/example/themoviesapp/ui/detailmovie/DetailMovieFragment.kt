package com.example.themoviesapp.ui.detailmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.core.data.Resource
import com.example.core.domain.model.Movie
import com.example.themoviesapp.databinding.FragmentDetailMovieBinding
import org.koin.android.viewmodel.ext.android.viewModel


class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModel()
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
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movie = movie
    }

    private fun observe(){
        movie?.let { movie ->
            detailViewModel.fetchReview(movieId = movie.id, 1).observe(viewLifecycleOwner, Observer { data ->
                data?.let { result ->
                    when(result){
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {

                        }
                        is Resource.Error -> {

                        }
                    }
                }

            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}