package com.tasks.appsfactorytask.ui.artistablums.domain.repository

import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.data.remote.network.response.ArtistAlbumsResponse
import io.reactivex.Single

interface ArtistAlbumsRepository {
    fun getArtistAlbums(artistName: String): Single<ArtistAlbumsResponse>

    fun insertArtistAlbums(artistAlbums: List<AlbumEntity>): Single<Boolean>

    fun deleteArtistAlbums(artistAlbums: List<AlbumEntity>): Single<Boolean>
}