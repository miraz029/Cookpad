package com.cookpad.hiring.android.feature.recipe

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.cookpad.hiring.android.feature.recipe.databinding.FragmentRecipeListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    private lateinit var recipeListAdapter: RecipeListAdapter
    private val viewModel: RecipeListViewModel by viewModels()

    private var _binding: FragmentRecipeListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRecipeListBinding.bind(view)

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
                        is RecipeListViewState.Success -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            recipeListAdapter.submitList(viewState.recipes)
                        }
                        RecipeListViewState.Error -> {
                            binding.loadingCircularProgressIndicator.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                R.string.generic_error_message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        RecipeListViewState.Loading -> {
                            binding.loadingCircularProgressIndicator.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recipeList.apply {
            recipeListAdapter = RecipeListAdapter(
                onFavoriteSelection = { item ->
                    viewModel.setFavorite(item)
                }
            )

            adapter = recipeListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}