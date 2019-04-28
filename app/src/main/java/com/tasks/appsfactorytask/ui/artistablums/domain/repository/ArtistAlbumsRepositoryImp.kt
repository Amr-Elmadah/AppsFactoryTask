package com.tasks.appsfactorytask.ui.artistablums.domain.repository

import com.tasks.appsfactorytask.data.local.entity.AlbumEntity
import com.tasks.appsfactorytask.data.remote.network.response.ArtistAlbumsResponse
import com.tasks.appsfactorytask.ui.artistablums.data.local.ArtistAlbumsLocalDataSource
import com.tasks.appsfactorytask.ui.artistablums.data.remote.ArtistAlbumsRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class ArtistAlbumsRepositoryImp @Inject constructor(
    private val remoteDataSource: ArtistAlbumsRemoteDataSource,
    private val localDataSource: ArtistAlbumsLocalDataSource
) : ArtistAlbumsRepository {
    override fun insertArtistAlbums(artistAlbums: List<AlbumEntity>): Single<Boolean> =
        localDataSource.insertArtistAlbums(albums = artistAlbums)

    override fun deleteArtistAlbums(artistAlbums: List<AlbumEntity>): Single<Boolean> =
        localDataSource.deleteArtistAlbums(artistAlbums)

    override fun getArtistAlbums(artistName: String): Single<ArtistAlbumsResponse> =
        remoteDataSource.getArtistAlbums(artistName)
}