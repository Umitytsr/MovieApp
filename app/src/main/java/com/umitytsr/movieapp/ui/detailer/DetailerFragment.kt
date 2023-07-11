package com.umitytsr.movieapp.ui.detailer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.databinding.FragmentDetailerBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.Extensions.getReformatDate
import com.umitytsr.movieapp.domain.model.Movie
import com.umitytsr.movieapp.ui.home.HomeViewModel
import com.umitytsr.movieapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailerFragment : Fragment() {
    private lateinit var binding: FragmentDetailerBinding
    private val args: DetailerFragmentArgs by navArgs()
    private val viewModel: DetailerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refreshFavorites()
        args.movie?.id?.let {
            viewModel.initIsFavorite(it)
        }
        binding.apply {
            title.text = args.movie?.title
            overview.text = args.movie?.overview
            voteAverage.text = args.movie?.voteAverage?.format(1)
            releaseDate.text = getReformatDate(args.movie?.releaseDate)

            addFavorite.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    addMovieToFavorites(args.movie!!)
                }else{
                    removeMovieFromFavorites(args.movie!!)
                }
            }
        }
        Glide.with(requireContext()).load(Constants.IMAGE_URL.plus(args.movie?.backdropPath))
            .into(binding.backdropImageView)


        viewLifecycleOwner.lifecycleScope.launch() {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.isFavorite.collectLatest {
                      binding.addFavorite.isChecked = it
                }
            }
        }
    }

    private fun addMovieToFavorites(movie: Movie) {
        viewModel.addMovieToFavorites(movie)
    }

    private fun removeMovieFromFavorites(movie: Movie) {
        viewModel.removeMovieFromFavorites(movie)
    }
}