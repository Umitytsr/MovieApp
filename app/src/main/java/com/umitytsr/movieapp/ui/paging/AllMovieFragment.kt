package com.umitytsr.movieapp.ui.paging

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.umitytsr.movieapp.data.model.movie.ResultMovie
import com.umitytsr.movieapp.data.model.series.ResultTvSeries
import com.umitytsr.movieapp.databinding.FragmentAllMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllMovieFragment : Fragment() {
    private lateinit var binding: FragmentAllMovieBinding
    private val args: AllMovieFragmentArgs by navArgs()
    private val viewModel: AllMovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllMovieBinding.inflate(inflater, container, false)
        collectData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument = args.string as? String
        argument?.let { arg ->
            if (arg.equals("a")){
                binding.allMovieTextView.text = "All Movie"
            }else{
                binding.allMovieTextView.text = "All Tv Series"
            }
        }
    }

    private fun collectData() {
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        val argument = args.string as? String

        lifecycleScope.launch {
            argument?.let { arg ->
                if (arg == "a") {
                    viewModel.getMovieList().observe(viewLifecycleOwner) {
                        initRecylerViewMovie(it)
                    }
                } else {
                    viewModel.getTvSeriesList().observe(viewLifecycleOwner) {
                        initRecylerViewTvSeries(it)
                    }
                }
            }
        }
    }

    private fun initRecylerViewMovie(movie: PagingData<ResultMovie>) {
        val _adapter = MoviePagerAdapter()
        val spanCount = 2
        binding.pagingRecyclerView.adapter = _adapter
        binding.pagingRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
        _adapter.addLoadStateListener {
            addLoadStateListener(it)
        }
        _adapter.submitData(lifecycle, movie)
    }

    private fun initRecylerViewTvSeries(tvSeries: PagingData<ResultTvSeries>) {
        val _adapter = TvSeriesPagerAdapter()
        val spanCount = 2
        binding.pagingRecyclerView.adapter = _adapter
        binding.pagingRecyclerView.layoutManager = GridLayoutManager(requireContext(), spanCount)
        _adapter.addLoadStateListener {
            addLoadStateListener(it)
        }
        _adapter.submitData(lifecycle, tvSeries)
    }

    fun addLoadStateListener(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading ||
            loadState.append is LoadState.Loading
        )
        else {
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}