package com.umitytsr.movieapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.umitytsr.movieapp.data.model.favorite.Favorite
import com.umitytsr.movieapp.databinding.FragmentFavoriteBinding
import com.umitytsr.movieapp.domain.Extensions.toMovieForFavorite
import com.umitytsr.movieapp.ui.detailer.DetailerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment(), FavoriteAdapter.FavoriteItemClickListener {
    private lateinit var binding: FragmentFavoriteBinding
    private val detailerViewModel: DetailerViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        collectData()
        return binding.root
    }

    private fun collectData() {
        detailerViewModel.refreshFavorites()
        viewLifecycleOwner.lifecycleScope.launch() {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailerViewModel.propertiesFavorite.collectLatest {
                    if (it.isEmpty()){
                        binding.emptyText.visibility = View.VISIBLE
                        binding.favoriteRecyclerView.visibility = View.GONE
                        binding.allFavoritesTextView.visibility = View.GONE
                    }else {
                        binding.emptyText.visibility = View.GONE
                        binding.favoriteRecyclerView.visibility = View.VISIBLE
                        binding.allFavoritesTextView.visibility = View.VISIBLE
                        initRecylerViewMovie(it)
                    }
                }
            }
        }
    }

    private fun initRecylerViewMovie(favorite: List<Favorite>) {
        val spanCount = 2 // Sütun sayısı
        val adapter = FavoriteAdapter(favorite, this@FavoriteFragment)
        val layoutManager = GridLayoutManager(requireContext(), spanCount)
        binding.favoriteRecyclerView.apply {
            this.adapter = adapter
            this.layoutManager = layoutManager
            setHasFixedSize(true)
        }
    }

    override fun favoriteItemClicked(favorite: Favorite) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailerFragment(
                favorite.toMovieForFavorite()
            )
        )
    }
}