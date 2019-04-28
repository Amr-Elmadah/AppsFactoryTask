package com.tasks.appsfactorytask.ui.home.local

import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.data.remote.network.response.Album
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

fun Album.map(): AlbumEntity {
    val image: String = this.image.filter {
        it.size == "large"
    }.map {
        it.text
    }.first()
    return AlbumEntity(
        albumImage = image
        , artistName = this.artist.name
        , albumPlayCount = this.playcount
        , albumName = this.name,
        albumUrl = this.url
    )
}

fun Album.mapToUI(): AlbumItem {
    val image: String = this.image.filter {
        it.size == "large"
    }.map {
        it.text
    }.first()
    return AlbumItem(
        albumImage = image
        , artistName = this.artist.name
        , albumPlayCount = this.playcount
        , albumName = this.name,
        albumUrl = this.url
    )
}