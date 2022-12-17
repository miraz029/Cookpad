package com.cookpad.hiring.android.core.network

import com.cookpad.hiring.android.core.network.model.RecipeDTO
import retrofit2.http.GET

interface IRecipeRemoteDataSource {

    @GET("api/collections/")
    suspend fun getRecipes(): List<RecipeDTO>
}