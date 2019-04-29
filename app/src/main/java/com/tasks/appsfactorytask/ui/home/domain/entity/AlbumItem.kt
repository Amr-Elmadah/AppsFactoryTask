package com.tasks.appsfactorytask.ui.home.domain.entity

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.tasks.appsfactorytask.base.presentation.view.extension.loadFromUrl
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumItem(
    var albumName: String,
    var albumPlayCount: Long,
    var albumUrl: String,
    var artistName: String,
    var albumImage: String
) : Parcelable

@BindingAdapter("bind:backgroundImageUrl")
fun loadBackgroundImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let { view.loadFromUrl(it) }
}