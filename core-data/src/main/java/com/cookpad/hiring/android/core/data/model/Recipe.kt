package com.cookpad.hiring.android.core.data.model

import com.cookpad.hiring.android.core.database.model.PreviewImages
import com.cookpad.hiring.android.core.database.model.RecipeEntity
import com.cookpad.hiring.android.core.network.model.RecipeDTO


fun RecipeDTO.asEntity() = RecipeEntity(
    id = id,
    title = title,
    description = description,
    recipeCount = recipeCount,
    previewImages = PreviewImages(previewImageUrls = previewImageUrls),
    isFavorite = false
)