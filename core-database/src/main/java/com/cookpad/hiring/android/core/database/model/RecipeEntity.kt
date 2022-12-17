package com.cookpad.hiring.android.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cookpad.hiring.android.core.model.data.Recipe

/**
 * Defines an Cookpad Recipe
 */
@Entity(tableName = "recipes")
data class RecipeEntity(

    @PrimaryKey
    val id: Int,
    val title: String?,
    val description: String?,
    val recipeCount: Int?,
    val previewImages: PreviewImages,
    val isFavorite: Boolean
)

class PreviewImages(var previewImageUrls: List<String>)

fun RecipeEntity.asExternalModel() = Recipe(
    id = id,
    title = title,
    description = description,
    recipeCount = recipeCount,
    previewImages = previewImages.previewImageUrls,
    isFavorite = isFavorite
)