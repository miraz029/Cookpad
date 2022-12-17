package com.cookpad.hiring.android.feature.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookpad.hiring.android.core.model.data.Recipe
import com.cookpad.hiring.android.feature.recipe.databinding.RecipeListItemBinding

/**
 *
 * This class creates the view holder for Recipe List Recycler View
 *
 * @param binding View Binding element of Recipe List Item
 * @param onFavoriteSelection The lambda function to invoke if user press the Favorite Icon
 */
class RecipeListViewHolder(
    private val binding: RecipeListItemBinding,
    private val onFavoriteSelection: (Recipe) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Recipe) {
        Glide.with(binding.root.context)
            .load(item.previewImages.firstOrNull())
            .centerCrop()
            .into(binding.recipeImageView)

        binding.recipeNameTextView.text = item.title
        binding.descriptionTextView.text = item.description
        binding.recipeCountTextView.text = binding.root.context.getString(
            R.string.recipe_count,
            item.recipeCount.toString()
        )

        if (item.isFavorite) {
            binding.favouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.favouriteImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        binding.favouriteImageView.setOnClickListener {
            onFavoriteSelection.invoke(item)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onFavoriteSelection: (Recipe) -> Unit
        ): RecipeListViewHolder {
            val restaurantListItemBinding =
                RecipeListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return RecipeListViewHolder(restaurantListItemBinding, onFavoriteSelection)
        }
    }
}