package com.umitytsr.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), MovieAdapter.MovieItemClickListener{
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        collectData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
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

    private fun collectData(){
        viewLifecycleOwner.lifecycleScope.launch() {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                with(viewModel){
                    launch {
                        propertiesMovie.collectLatest {
                            initRecylerViewMovie(it)
                        }
                    }
                    launch {
                        propertiesTvSeries.collectLatest {
                            initRecylerViewMovie(it)
                        }
                    }
                }
            }
        }
    }

    private fun initRecylerViewMovie(movie: List<Movie>){
        val _adapter = MovieAdapter(movie,this@HomeFragment)
        val _layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        if (movie == viewModel.propertiesMovie.value){
            with(binding.populerMovieRecyclerView){
                adapter = _adapter
                layoutManager = _layoutManager
                setHasFixedSize(true)
            }
        }else {
            with(binding.populerTvSeriesRecyclerView){
                adapter = _adapter
                layoutManager = _layoutManager
                setHasFixedSize(true)
            }
        }

    }

    override fun movieItemClicked(movie: Movie) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailerFragment(movie)
        )
    }
}