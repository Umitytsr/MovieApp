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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.databinding.FragmentHomeBinding
import com.umitytsr.movieapp.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
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
        lifecycleScope.launch() {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.properties.collectLatest {
                    initRecylerView(it)
                }
            }
        }
    }

    private fun initRecylerView(movie: List<Movie>){
        val _adapter = MovieAdapter(movie)
        with(binding.populerMovieRecyclerView){
            adapter = _adapter
            layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }
}