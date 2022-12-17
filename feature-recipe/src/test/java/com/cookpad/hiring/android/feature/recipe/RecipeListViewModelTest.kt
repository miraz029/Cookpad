package com.cookpad.hiring.android.feature.recipe

import com.cookpad.hiring.android.core.data.repository.RecipeRepository
import com.cookpad.hiring.android.core.model.data.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RecipeListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var recipeListViewModel: RecipeListViewModel
    private val recipeRepository: RecipeRepository = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given recipes are loaded when data source is success then emit success view state`() {
        val expectedRecipes = getRecipes()
        runTest {
            whenever(recipeRepository.getAllRecipes()).thenReturn(flow { emit(expectedRecipes) })
            recipeListViewModel = RecipeListViewModel(recipeRepository)

            val stateFlow = recipeListViewModel.viewState.first()

            assertEquals(stateFlow, RecipeListViewState.Success(expectedRecipes))
        }
    }

    @Test
    fun `given recipes are loaded when data source is error then emit error view state`() {
        runTest {
            whenever(recipeRepository.getAllRecipes()).thenThrow(RuntimeException(""))
            recipeListViewModel = RecipeListViewModel(recipeRepository)

            val stateFlow = recipeListViewModel.viewState.first()

            assertEquals(stateFlow, RecipeListViewState.Error)
        }
    }

    private fun getRecipes(): List<Recipe> {
        val element = Recipe(
            id = 1,
            title = "title",
            description = "disc",
            recipeCount = 3,
            previewImages = listOf("url"),
            false
        )
        return listOf(element)
    }
}