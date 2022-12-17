package com.cookpad.hiring.android.feature.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookpad.hiring.android.feature.favorite.databinding.FragmentFavoriteListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteListFragment : Fragment(R.layout.fragment_favorite_list) {

    private lateinit var favoriteListAdapter: FavoriteListAdapter
    private val viewModel: FavoriteListViewModel by viewModels()

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFavoriteListBinding.bind(view)

        setUpRecyclerView()

        binding.swipeToRefresh.apply {
            setOnRefreshListener {
                isRefreshing = false
                viewModel.refresh()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    when (viewState) {
                        is FavoriteListViewState.Success -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            favoriteListAdapter.submitList(viewState.recipes)
                        }
                        FavoriteListViewState.Error -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                R.string.generic_error_message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        FavoriteListViewState.Loading -> {
                            binding.loadingCircularProgressIndicator.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.favoriteList.apply {
            favoriteListAdapter = FavoriteListAdapter(
                onFavoriteSelection = { item ->
                    viewModel.setFavorite(item)
                }
            )

            adapter = favoriteListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}