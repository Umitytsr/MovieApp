package com.umitytsr.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.movieapp.databinding.FragmentHomeBinding
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieAdapter.MovieItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        collectData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            seeAllMovie.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToAllMovieFragment("a")
                )
            }
            seeAllTvSeries.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToAllMovieFragment("b")
                )
            }
        }
    }

    private fun collectData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(viewModel) {
                    launch {
                        propertiesMovie.collectLatest { resource ->
                            when (resource) {
                                is Resource.Loading -> {
                                    // Show loading indicator
                                }

                                is Resource.Success -> {
                                    initRecyclerViewMovie(resource.data,binding.populerMovieRecyclerView)
                                }

                                is Resource.Error -> {
                                    Toast.makeText(
                                        requireContext(), "Check your internet",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                    launch {
                        propertiesTvSeries.collectLatest { resource ->
                            when (resource) {
                                is Resource.Loading -> {
                                    // Show loading indicator
                                }

                                is Resource.Success -> {
                                    initRecyclerViewMovie(resource.data,binding.populerTvSeriesRecyclerView)
                                }

                                is Resource.Error -> {
                                    Toast.makeText(
                                        requireContext(), "Check your internet",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initRecyclerViewMovie(movie: List<Movie>, recyclerView: RecyclerView) {
        val _adapter = MovieAdapter(movie, this@HomeFragment)
        val _layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerView.apply {
            adapter = _adapter
            layoutManager = _layoutManager
            setHasFixedSize(true)
        }
    }

    override fun movieItemClicked(movie: Movie) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailerFragment(movie)
        )
    }
}