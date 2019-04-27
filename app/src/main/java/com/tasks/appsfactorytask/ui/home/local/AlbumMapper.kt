package com.tasks.appsfactorytask.ui.home.local

import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.ui.home.domain.entity.AlbumItem


fun AlbumEntity.mapToUI(): AlbumItem {
    return AlbumItem(
        albumImage = albumImage
        , artistName = artistName
        , albumPlayCount = albumPlayCount
        , albumName = albumName,
        albumUrl = albumUrl
    )
}