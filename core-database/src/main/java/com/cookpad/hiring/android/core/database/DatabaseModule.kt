package com.cookpad.hiring.android.core.database

import android.content.Context
import androidx.room.Room
import com.cookpad.hiring.android.core.database.dao.IRecipeLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCookpadDatabase(
        @ApplicationContext context: Context
    ): CookpadDatabase =
        Room.databaseBuilder(context, CookpadDatabase::class.java, "cookpad.db")
            .fallbackToDestructiveMigration().build()

    @Provides
    fun provideRecipeDao(
        database: CookpadDatabase
    ): IRecipeLocalDataSource = database.recipeDao()
}