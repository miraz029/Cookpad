package com.cookpad.hiring.android.feature.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookpad.hiring.android.core.model.data.Recipe
import com.cookpad.hiring.android.feature.favorite.databinding.FavoriteListItemBinding

/**
 *
 * This class creates the view holder for Favorite List Recycler View
 *
 * @param binding View Binding element of Favorite List Item
 * @param onFavoriteSelection The lambda function to invoke if user press the Favorite Icon
 */
class FavoriteListViewHolder(
    private val binding: FavoriteListItemBinding,
    private val onFavoriteSelection: (Recipe) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Recipe) {
        Glide.with(binding.root.context)
            .load(item.previewImages.firstOrNull())
            .centerCrop()
            .into(binding.recipeImageView)

        binding.recipeNameTextView.text = item.title
//        binding.descriptionTextView.text = item.description
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
        ): FavoriteListViewHolder {
            val restaurantListItemBinding =
                FavoriteListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return FavoriteListViewHolder(restaurantListItemBinding, onFavoriteSelection)
        }
    }
}