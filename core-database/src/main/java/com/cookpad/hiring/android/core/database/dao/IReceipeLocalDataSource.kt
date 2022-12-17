package com.cookpad.hiring.android.core.database.dao

import androidx.room.*
import com.cookpad.hiring.android.core.database.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [RecipeEntity] access
 */
@Dao
interface IRecipeLocalDataSource {

    /**
     * Get all *recipes* items.
     *
     * @return the flow of all recipes
     */
    @Query(value = "SELECT * FROM recipes")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    /**
     * Inserts [recipes] into the db if they don't exist, and ignores those that do
     *
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreRecipes(recipes: List<RecipeEntity>)

    /**
     * Update Recipe
     *
     * Here [id] is the ID of the Recipe and [isFavorite] is the updated favorite state
     *
     * */
    @Query("UPDATE recipes SET isFavorite=:isFavorite WHERE id = :id")
    suspend fun updateRecipe(id: Int, isFavorite: Boolean)

    /**
     * Function to get all favorite recipes
     *
     * @return the flow of all recipes
     */
    @Query("SELECT * FROM recipes WHERE isFavorite = 1 ORDER BY id")
    fun getFavoriteRecipes(): Flow<List<RecipeEntity>>
}

