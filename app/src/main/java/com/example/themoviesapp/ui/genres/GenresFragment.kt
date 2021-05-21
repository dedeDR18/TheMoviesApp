package com.example.themoviesapp.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.themoviesapp.databinding.FragmentGenresBinding
import org.koin.android.viewmodel.ext.android.viewModel


class GenresFragment : Fragment() {

    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!
    private val genresViewModel: GenresViewModel by viewModel()


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

        observe()
    }

    private fun observe() {
        genresViewModel.genres.observe(viewLifecycleOwner, Observer { data ->
            data?.let { list ->
                val genreName = list.map {
                    it.name
                }
                initListView(genreName)
            }
        })
    }

    private fun initListView(data: List<String>) {
        val arrayAdapter: ArrayAdapter<String>
        binding.lvGenre.apply {
            arrayAdapter =
                ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, data)
            adapter = arrayAdapter

           onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
               val selectedItemText = parent.getItemAtPosition(position)
               Toast.makeText(requireContext(), "clicked = ".plus(selectedItemText), Toast.LENGTH_SHORT).show()
           }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}