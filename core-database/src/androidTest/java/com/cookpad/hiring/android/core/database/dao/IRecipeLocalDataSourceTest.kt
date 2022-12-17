package com.cookpad.hiring.android.core.database.dao


import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.cookpad.hiring.android.core.database.CookpadDatabase
import com.cookpad.hiring.android.core.database.model.PreviewImages
import com.cookpad.hiring.android.core.database.model.RecipeEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
class IRecipeLocalDataSourceTest {

    private lateinit var iRecipeLocalDataSource: IRecipeLocalDataSource
    private lateinit var cookpadDatabase: CookpadDatabase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        cookpadDatabase = Room.inMemoryDatabaseBuilder(context, CookpadDatabase::class.java).build()
        iRecipeLocalDataSource = cookpadDatabase.recipeDao()
    }

    @After
    fun closeDb() {
        cookpadDatabase.close()
    }

    @Test
    fun insert_recipes_test() = runBlocking(Dispatchers.IO) {
        iRecipeLocalDataSource.insertOrIgnoreRecipes(recipes)

        val cursor = cookpadDatabase.query("SELECT * FROM recipes", null).apply { moveToNext() }
        assertEquals(2, cursor.count)
        assertEquals(1, cursor.getInt(cursor.getColumnIndex("id")))
        assertEquals("title 1", cursor.getString(cursor.getColumnIndex("title")))
        assertEquals("disc 1", cursor.getString(cursor.getColumnIndex("description")))
        assertEquals(0, cursor.getInt(cursor.getColumnIndex("isFavorite")))
    }

    @Test
    fun add_recipe_to_favorite_test() = runBlocking(Dispatchers.IO) {
        iRecipeLocalDataSource.insertOrIgnoreRecipes(recipes)

        val cursor = cookpadDatabase.query("SELECT * FROM recipes where id=${recipe1.id}", null)
            .apply { moveToNext() }
        assertEquals(0, cursor.getInt(cursor.getColumnIndex("isFavorite")))

        iRecipeLocalDataSource.updateRecipe(recipe1.id, !recipe1.isFavorite)
        val cursor2 = cookpadDatabase.query("SELECT * FROM recipes where id=${recipe1.id}", null)
            .apply { moveToNext() }
        assertEquals(1, cursor2.getInt(cursor.getColumnIndex("isFavorite")))
    }

    @Test
    fun remove_recipe_from_favorite_test() = runBlocking(Dispatchers.IO) {
        iRecipeLocalDataSource.insertOrIgnoreRecipes(recipes)

        val cursor = cookpadDatabase.query("SELECT * FROM recipes where id=${recipe2.id}", null)
            .apply { moveToNext() }
        assertEquals(1, cursor.getInt(cursor.getColumnIndex("isFavorite")))

        iRecipeLocalDataSource.updateRecipe(recipe2.id, !recipe2.isFavorite)
        val cursor2 = cookpadDatabase.query("SELECT * FROM recipes where id=${recipe2.id}", null)
            .apply { moveToNext() }
        assertEquals(0, cursor2.getInt(cursor.getColumnIndex("isFavorite")))
    }

    companion object {

        private val recipe1 = RecipeEntity(
            id = 1,
            title = "title 1",
            description = "disc 1",
            recipeCount = 3,
            previewImages = PreviewImages(listOf("url 1")),
            isFavorite = false
        )

        private val recipe2 = RecipeEntity(
            id = 2,
            title = "title 2",
            description = "disc 2",
            recipeCount = 3,
            previewImages = PreviewImages(listOf("url 2")),
            isFavorite = true
        )

        val recipes = listOf(recipe1, recipe2)
    }
}