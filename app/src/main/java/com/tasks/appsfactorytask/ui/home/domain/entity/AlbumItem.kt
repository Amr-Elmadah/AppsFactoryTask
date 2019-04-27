package com.tasks.appsfactorytask.ui.home.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumItem(
    var albumName: String,
    var albumPlayCount: Long,
    var albumUrl: String,
    var artistName: String,
    var albumImage: String
) : Parcelable