package com.cookpad.hiring.android.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.hiring.android.core.data.repository.RecipeRepository
import com.cookpad.hiring.android.core.model.data.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.cookpad.hiring.android.feature.favorite.FavoriteListViewState.*

@HiltViewModel
class FavoriteListViewModel @Inject constructor(private val repository: RecipeRepository) :
    ViewModel() {

    private val _viewState = MutableStateFlow<FavoriteListViewState>(
        Success(
            emptyList()
        )
    )
    val viewState: StateFlow<FavoriteListViewState> = _viewState

    init {
        loadRecipes()
    }

    fun refresh() {
        loadRecipes()
    }

    private fun loadRecipes() {
        _viewState.value = Loading

        viewModelScope.launch {
            runCatching {
                repository.getFavoriteRecipes()
            }.onFailure {
                _viewState.value = Error
            }.onSuccess { recipesFlow ->
                recipesFlow.collect { recipes ->
                    _viewState.value = Success(recipes)
                }
            }
        }
    }

    fun setFavorite(item: Recipe) {
        viewModelScope.launch {
            repository.updateRecipe(item.id, !item.isFavorite)
        }
    }
}

sealed class FavoriteListViewState {
    object Loading : FavoriteListViewState()
    object Error : FavoriteListViewState()
    data class Success(val recipes: List<Recipe>) : FavoriteListViewState()
}