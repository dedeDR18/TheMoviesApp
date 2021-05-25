package com.example.themoviesapp.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.core.domain.model.Genre
import com.example.themoviesapp.R
import com.example.themoviesapp.databinding.FragmentGenresBinding
import org.koin.android.viewmodel.ext.android.viewModel


class GenresFragment : Fragment() {

    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!
    private val genresViewModel: GenresViewModel by viewModel()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        observe()
    }

    private fun observe() {
        genresViewModel.genres.observe(viewLifecycleOwner, Observer { data ->
            data?.let { list ->
                initListView(list)
            }
        })
    }

    private fun initListView(data: List<Genre>) {
        val name = data.map {
            it.name
        }
        val arrayAdapter: ArrayAdapter<String>
        binding.lvGenre.apply {
            arrayAdapter =
                ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, name)
            adapter = arrayAdapter

            onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                navigateToMovieFragment(data[position])
            }
        }
    }

    fun navigateToMovieFragment(genre: Genre) {
        val bundle = bundleOf(
            "genre" to genre
        )
        navController.navigate(R.id.action_genresFragment_to_moviesFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}