package com.cookpad.hiring.android.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cookpad.hiring.android.core.database.dao.IRecipeLocalDataSource
import com.cookpad.hiring.android.core.database.model.RecipeEntity
import com.cookpad.hiring.android.core.database.util.PreviewImagesConverter

@Database(
    entities = [RecipeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PreviewImagesConverter::class)
abstract class CookpadDatabase : RoomDatabase() {

    abstract fun recipeDao(): IRecipeLocalDataSource
}