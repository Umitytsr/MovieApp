package com.umitytsr.movieapp.ui.paging

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.umitytsr.movieapp.databinding.FragmentAllMovieBinding
import com.umitytsr.movieapp.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllMovieFragment : Fragment(), MoviePagerAdapter.MoviePagerItemClickListener {
    private lateinit var binding: FragmentAllMovieBinding
    private val args: AllMovieFragmentArgs by navArgs()
    private val viewModel: AllMovieViewModel by viewModels()
    private lateinit var layoutManager: GridLayoutManager
    private var isDataCollected = false
    private var savedScrollPosition = 0
    private lateinit var savedMovieData: PagingData<Movie>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllMovieBinding.inflate(inflater, container, false)
        collectData()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        layoutManager.scrollToPosition(savedScrollPosition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argument = args.string as? String
        argument?.let { arg ->
            if (arg.equals("a")) {
                binding.allMovieTextView.text = "All Movie"
            } else {
                binding.allMovieTextView.text = "All Tv Series"
            }
        }
    }

    private fun collectData() {
        if (!isDataCollected) {
            viewModel.errorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Check your Internet", Toast.LENGTH_SHORT).show()
            }
            val argument = args.string as? String

            lifecycleScope.launch {
                argument?.let { arg ->
                    if (arg == "a") {
                        viewModel.getMovieList().observe(viewLifecycleOwner) {
                            savedMovieData = it
                            initRecylerViewMovie(it)
                        }
                    } else {
                        viewModel.getTvSeriesList().observe(viewLifecycleOwner) {
                            savedMovieData = it
                            initRecylerViewMovie(it)
                        }
                    }
                }
            }
            isDataCollected = true
        } else {
            initRecylerViewMovie(savedMovieData)
            layoutManager.scrollToPosition(savedScrollPosition)
        }
    }

    private fun initRecylerViewMovie(movie: PagingData<Movie>) {
        val _adapter = MoviePagerAdapter(this@AllMovieFragment)
        val spanCount = 2
        binding.pagingRecyclerView.adapter = _adapter
        layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.pagingRecyclerView.layoutManager = layoutManager
        _adapter.addLoadStateListener {
            addLoadStateListener(it)
        }
        _adapter.submitData(lifecycle, movie)
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

    override fun movieItemClicked(movie: Movie, position: Int) {
        savedScrollPosition = position
        findNavController().navigate(
            AllMovieFragmentDirections.actionAllMovieFragmentToDetailerFragment(movie)
        )
    }
}