package com.tasks.appsfactorytask.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Album")
data class AlbumEntity(
    @PrimaryKey
    @ColumnInfo(name = "album_name")
    var albumName: String,
    @ColumnInfo(name = "play_count")
    var albumPlayCount: Long,
    @ColumnInfo(name = "url")
    var albumUrl: String,
    @ColumnInfo(name = "artist_name")
    var artistName: String,
    @ColumnInfo(name = "album_image")
    var albumImage: String
)