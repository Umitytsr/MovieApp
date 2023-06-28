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
import com.umitytsr.movieapp.domain.model.TvSeries
import com.umitytsr.movieapp.ui.home.adapter.MovieAdapter
import com.umitytsr.movieapp.ui.home.adapter.TvSeriesAdapter
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
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        collectData()
        return binding.root
    }

    private fun collectData(){
        viewLifecycleOwner.lifecycleScope.launch() {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                with(viewModel){
                    propertiesMovie.collectLatest {
                        initRecylerViewMovie(it)
                    }
                    propertiesTvSeries.collectLatest {
                        initRecylerViewTvSeries(it)
                    }
                }
            }
        }
    }

    private fun initRecylerViewMovie(movie: List<Movie>){
        val _adapter = MovieAdapter(movie,this@HomeFragment)
        with(binding.populerMovieRecyclerView){
            adapter = _adapter
            layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

    private fun initRecylerViewTvSeries(tvSeries: List<TvSeries>){
        val _adapter = TvSeriesAdapter(tvSeries)
        with(binding.populerTvSeriesRecyclerView){
            adapter = _adapter
            layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

    override fun movieIntemClicked(movie: Movie) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailerFragment(movie,null)
        )
    }
}