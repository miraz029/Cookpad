package com.cookpad.hiring.android.feature.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookpad.hiring.android.core.data.repository.RecipeRepository
import com.cookpad.hiring.android.core.model.data.Recipe
import com.cookpad.hiring.android.feature.recipe.RecipeListViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val repository: RecipeRepository) :
    ViewModel() {

    private val _viewState = MutableStateFlow<RecipeListViewState>(Success(emptyList()))
    val viewState: StateFlow<RecipeListViewState> = _viewState

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
                repository.getAllRecipes()
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

sealed class RecipeListViewState {
    object Loading : RecipeListViewState()
    object Error : RecipeListViewState()
    data class Success(val recipes: List<Recipe>) : RecipeListViewState()
}