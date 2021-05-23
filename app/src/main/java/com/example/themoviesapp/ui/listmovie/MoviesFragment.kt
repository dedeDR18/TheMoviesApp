package com.example.themoviesapp.ui.listmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.core.domain.model.Genre
import com.example.themoviesapp.R
import com.example.themoviesapp.databinding.FragmentMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private var genre: Genre? = null
    private val moviesViewModel:MoviesViewModel by viewModel()

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
        observe()
    }

    private fun observe(){
        genre?.let { genre ->
            moviesViewModel.fetchMoviesByGenre(genre.id)
            moviesViewModel.movies(genre.id).observe(viewLifecycleOwner, Observer { listMovie ->
                Toast.makeText(requireContext(), "Jumlah data = ${listMovie.size}", Toast.LENGTH_SHORT).show()
            })
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}