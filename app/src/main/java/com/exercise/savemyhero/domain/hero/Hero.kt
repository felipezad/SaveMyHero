package com.exercise.savemyhero.domain.hero

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.exercise.savemyhero.domain.hero.Hero.Companion.TABLE_NAME
import com.exercise.savemyhero.ui.core.ThumbnailOrientation
import com.exercise.savemyhero.ui.core.ThumbnailSize
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class Hero(
    @PrimaryKey
    var id: Int? = 0,
    var name: String = "",
    var thumbnail: String = "",
    var extension: String = "",
    var description: String = ""
) : Parcelable {
    companion object {
        const val TABLE_NAME = "hero"
    }
}

fun Hero.imageUrl(orientation: ThumbnailOrientation, size: ThumbnailSize): String {
    var imageVariantUrl = ""
    imageVariantUrl += when (orientation) {
        ThumbnailOrientation.PORTRAIT -> {
            orientation.path
        }
        ThumbnailOrientation.LANDSCAPE -> {
            orientation.path
        }
    }
    imageVariantUrl += when (size) {
        ThumbnailSize.SMALL -> {
            size.path
        }
        ThumbnailSize.MEDIUM -> {
            size.path
        }
        ThumbnailSize.LARGE -> {
            size.path
        }
    }
    return thumbnail + imageVariantUrl + extension
}
