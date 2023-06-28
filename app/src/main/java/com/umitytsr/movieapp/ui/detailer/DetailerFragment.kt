package com.umitytsr.movieapp.ui.detailer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.umitytsr.movieapp.R
import com.umitytsr.movieapp.databinding.FragmentDetailerBinding
import com.umitytsr.movieapp.domain.Extensions.format
import com.umitytsr.movieapp.domain.Extensions.getReformatDate
import com.umitytsr.movieapp.util.Constants

class DetailerFragment : Fragment() {
    private lateinit var binding: FragmentDetailerBinding
    private val args: DetailerFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            title.text = args.movie?.title
            overview.text = args.movie?.overview
            voteAverage.text = args.movie?.voteAverage?.format(1)
            releaseDate.text = getReformatDate(args.movie?.releaseDate)
        }
        Glide.with(requireContext()).load(Constants.IMAGE_URL.plus(args.movie?.backdropPath)).into(binding.backdropImageView)
    }
}