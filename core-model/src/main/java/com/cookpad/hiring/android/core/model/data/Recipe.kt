package com.cookpad.hiring.android.core.model.data

/**
 * External data layer representation of an Cookbook Recipe
 */
data class Recipe(
    val id: Int,
    val title: String?,
    val description: String?,
    val recipeCount: Int?,
    val previewImages: List<String>,
    val isFavorite: Boolean
)