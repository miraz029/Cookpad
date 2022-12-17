package com.cookpad.hiring.android.core.data.repository

import com.cookpad.hiring.android.core.database.dao.IRecipeLocalDataSource
import com.cookpad.hiring.android.core.database.model.PreviewImages
import com.cookpad.hiring.android.core.database.model.RecipeEntity
import com.cookpad.hiring.android.core.network.IRecipeRemoteDataSource
import com.cookpad.hiring.android.core.network.model.RecipeDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RecipeRepositoryTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val iRecipeRemoteDataSource: IRecipeRemoteDataSource = mock()
    private val iRecipeLocalDataSource: IRecipeLocalDataSource = mock()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when remote api returns success then repo should also return success with correct mapping`() {
        val recipesDTO = getRecipesDTO()
        val recipesFlow = getRecipesFlow()

        runTest {
            whenever(iRecipeRemoteDataSource.getRecipes()).thenReturn(recipesDTO)
            whenever(iRecipeLocalDataSource.getAllRecipes()).thenReturn(recipesFlow)

            val recipeRepository =
                RecipeRepository(iRecipeRemoteDataSource, iRecipeLocalDataSource)

            recipeRepository.getAllRecipes().collect { recipes ->
                assertEquals(recipes.size, recipesDTO.size)
                assertEquals(recipes.first().id, recipesDTO.first().id)
            }
        }
    }

    private fun getRecipesDTO(): List<RecipeDTO> {
        return listOf(
            RecipeDTO(
                id = 1,
                title = "title",
                description = "desc",
                recipeCount = 2,
                previewImageUrls = listOf("urls")
            )
        )
    }

    private fun getRecipesFlow() = flow {
        emit(
            getRecipesDTO().map { recipeDTO ->
                with(recipeDTO) {
                    RecipeEntity(
                        id = id,
                        title = title,
                        description = description,
                        recipeCount = recipeCount,
                        previewImages = PreviewImages(previewImageUrls),
                        isFavorite = false
                    )
                }
            }
        )
    }
}