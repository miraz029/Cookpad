package com.cookpad.hiring.android.core.database.util

import androidx.room.TypeConverter
import com.cookpad.hiring.android.core.database.model.PreviewImages

class PreviewImagesConverter {

    @TypeConverter
    fun storedStringToPreviewImages(value: String): PreviewImages {
        val previewImages: List<String> = value.split(",")
        return PreviewImages(previewImages)
    }

    @TypeConverter
    fun previewImagesToStoredString(previewImages: PreviewImages): String {
        var value = ""
        previewImages.previewImageUrls.forEach {
            value += "$it,"
        }
        return value
    }
}