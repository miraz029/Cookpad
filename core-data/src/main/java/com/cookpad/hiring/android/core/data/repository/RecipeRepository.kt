package com.cookpad.hiring.android.core.data.repository


import com.cookpad.hiring.android.core.data.model.asEntity
import com.cookpad.hiring.android.core.database.dao.IRecipeLocalDataSource
import com.cookpad.hiring.android.core.database.model.asExternalModel
import com.cookpad.hiring.android.core.model.data.Recipe
import com.cookpad.hiring.android.core.network.IRecipeRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RecipeRepository @Inject constructor(
    private val recipeRemoteDataSource: IRecipeRemoteDataSource,
    private val recipeLocalDataSource: IRecipeLocalDataSource
) {

    suspend fun getAllRecipes(): Flow<List<Recipe>> {
        val recipes = recipeRemoteDataSource.getRecipes().map { recipeDTO ->
            recipeDTO.asEntity()
        }
        recipeLocalDataSource.insertOrIgnoreRecipes(recipes)

        return recipeLocalDataSource.getAllRecipes().map {
            it.map { recipeEntity -> recipeEntity.asExternalModel() }
        }
    }

    suspend fun updateRecipe(id: Int, favoriteState: Boolean) {
        recipeLocalDataSource.updateRecipe(id, favoriteState)
    }

    suspend fun getFavoriteRecipes(): Flow<List<Recipe>> {
        return recipeLocalDataSource.getFavoriteRecipes().map {
            it.map { recipeEntity -> recipeEntity.asExternalModel() }
        }
    }
}