package com.cookpad.hiring.android.core.network.model

import com.squareup.moshi.Json

data class RecipeDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "recipe_count") val recipeCount: Int,
    @Json(name = "preview_image_urls") val previewImageUrls: List<String>
)